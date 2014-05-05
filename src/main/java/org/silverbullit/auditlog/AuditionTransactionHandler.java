package org.silverbullit.auditlog;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.silverbullit.auditlog.annotation.Auditable;
import org.silverbullit.auditlog.domain.Difference;
import org.silverbullit.auditlog.domain.DifferenceList;
import org.silverbullit.auditlog.domain.DifferenceType;

/**
 * Handles AuditionTransactions by creating a ThreadLocal
 * AuditionTransactionContext that keeps track of all detected differences.
 * 
 * @author Christian Ober
 */
@Aspect
public abstract class AuditionTransactionHandler {

	private static ThreadLocal<AuditionTransactionContext> auditionTransactionContext;

	/**
	 * detects differences on the given entity and records them within the context
	 * 
	 * @param auditableEntity
	 */
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
					default:
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

	/**
	 * Recursively retrieves all available fields for the given class.
	 * 
	 * @param fields
	 *          the fields
	 * @param type
	 *          the class to get all fields for (including superclass-fields)
	 * @return the list of retrieved fields
	 */
	private static List<Field> getAllFields(List<Field> fields, final Class<?> type) {
		for (final Field field : type.getDeclaredFields()) {
			fields.add(field);
		}

		if (type.getSuperclass() != null) {
			fields = getAllFields(fields, type.getSuperclass());
		}

		return fields;
	}

	abstract void handle(List<DifferenceList<String>> collectedDifferences);

	/**
	 * The pointcut-definition for the interceptor.
	 * 
	 * @param joinPoint
	 *          the reference to the intercepted method
	 * @return the result of the method (may be null)
	 * @throws Throwable
	 *           if any problem occurs
	 */
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
			this.handle(auditionTransactionContext.get().getRecordedDifferences());
			auditionTransactionContext.get().clear();
		}
	}
}
