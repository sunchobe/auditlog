package org.silverbullit.auditlog;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuditionTransactionHandler {

	private static final Logger logger = LogManager.getLogger(AuditionTransactionHandler.class);

	private static final ThreadLocal<AuditionTransactionContext> auditionTransactionContext = new ThreadLocal<>();

	public static void handleDetectedChanges(final AuditableEntity auditableEntity) {
		final DifferenceSet<String> differenceSet = auditableEntity.detectChanges();
		for (final Difference<?> difference : differenceSet) {
			logger.info("changes detected: {}", difference);
		}
	}

	public static void handleDetectedCreation(final AuditableEntity auditableEntity) {
		final DifferenceSet<String> differenceSet = auditableEntity.detectChanges();
		for (final Difference<?> difference : differenceSet) {
			logger.info("creation detected: {}", difference);
		}
	}

}
