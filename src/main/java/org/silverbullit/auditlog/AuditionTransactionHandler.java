package org.silverbullit.auditlog;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuditionTransactionHandler {

	private static final Logger logger = LogManager
			.getLogger(AuditionTransactionHandler.class);

	private static final ThreadLocal<AuditionTransactionContext> auditionTransactionContext = new ThreadLocal<>();

	public static void detectChanges(AuditableEntity savedEntity,
			AuditableEntity auditableEntity) {
		List<Field> fields = getAllFields(new ArrayList<Field>(), auditableEntity.getClass());
		for (Field field : fields) {
			if (field.isAnnotationPresent(Auditable.class)) {
				try {
					if (field.getModifiers() == Modifier.PRIVATE) {
						field.setAccessible(true);
					}
					logger.info("fieldname: {}, fieldvalue: {}", field.getName(), field.get(auditableEntity));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void detectNew(AuditableEntity auditableEntity) {
		// TODO Auto-generated method stub

	}

	private static List<Field> getAllFields(List<Field> fields, Class<?> type) {
		for (Field field : type.getDeclaredFields()) {
			fields.add(field);
		}

		if (type.getSuperclass() != null) {
			fields = getAllFields(fields, type.getSuperclass());
		}

		return fields;
	}
}
