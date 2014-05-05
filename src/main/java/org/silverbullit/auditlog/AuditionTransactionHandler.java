package org.silverbullit.auditlog;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.silverbullit.auditlog.annotation.Auditable;
import org.silverbullit.auditlog.domain.Difference;
import org.silverbullit.auditlog.domain.DifferenceList;
import org.silverbullit.auditlog.domain.DifferenceType;

@Aspect
public class AuditionTransactionHandler {

	private static final Logger logger = LogManager.getLogger(AuditionTransactionHandler.class);

	private static ThreadLocal<AuditionTransactionContext> auditionTransactionContext;

	public static void detectAndRecordChanges(final AuditableEntity auditableEntity) {

		final DifferenceList<String> differenceList = detectChanges(auditableEntity);
		auditionTransactionContext.get().record(differenceList);
	}

	private static DifferenceList<String> detectChanges(final AuditableEntity auditableEntity) {
		DifferenceType differenceType = DifferenceType.CREATION;
		if (auditableEntity.getSavedEntity() != null) {
			differenceType = DifferenceType.UPDATE;
		}
		final DifferenceList<String> differenceList = new DifferenceList<String>(differenceType, auditableEntity.getClass().getSimpleName());
		final List<Field> fields = getAllFields(new ArrayList<Field>(), auditableEntity.getClass());
		for (final Field field : fields) {
			if (field.isAnnotationPresent(Auditable.class)) {
				try {
					if (field.getModifiers() == Modifier.PRIVATE) {
						field.setAccessible(true);
					}

					Difference<Object> difference = null;
					switch (differenceType) {
					case CREATION:
						difference = new Difference<Object>(field.getName(), null, field.get(auditableEntity));
						break;
					case UPDATE:
						difference = new Difference<Object>(field.getName(), field.get(auditableEntity.getSavedEntity()), field.get(auditableEntity));
						break;
					}

					differenceList.add(difference);

				} catch (final Exception e) {
					e.printStackTrace();
				}
			}
		}
		return differenceList;
	}

	private static List<Field> getAllFields(List<Field> fields, final Class<?> type) {
		for (final Field field : type.getDeclaredFields()) {
			fields.add(field);
		}

		if (type.getSuperclass() != null) {
			fields = getAllFields(fields, type.getSuperclass());
		}

		return fields;
	}

	@Around("@annotation(org.silverbullit.auditlog.annotation.AuditionTransaction)")
	public Object handleAuditionTransaction(final ProceedingJoinPoint joinPoint) throws Throwable {
		if (auditionTransactionContext == null) {
			auditionTransactionContext = new ThreadLocal<>();
		}
		if (auditionTransactionContext.get() == null) {
			auditionTransactionContext.set(new AuditionTransactionContext());
		}

		try {
			return joinPoint.proceed();
		} finally {
			for (final DifferenceList<String> recordedDifference : auditionTransactionContext.get().getRecordedDifferences()) {
				logger.info("#####\n" + recordedDifference + "\n#####\n");
			}

			auditionTransactionContext.get().clear();
		}
	}
}
