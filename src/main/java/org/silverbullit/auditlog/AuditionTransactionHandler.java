package org.silverbullit.auditlog;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AuditionTransactionHandler {

	private static final Logger logger = LogManager.getLogger(AuditionTransactionHandler.class);

	private static ThreadLocal<AuditionTransactionContext> auditionTransactionContext;

	public static void detectAndRecordChanges(final AuditableEntity auditableEntity) {
		if (auditionTransactionContext == null) {
			auditionTransactionContext = new ThreadLocal<>();
		}
		if (auditionTransactionContext.get() == null) {
			auditionTransactionContext.set(new AuditionTransactionContext());
		}
		final DifferenceList<String> differenceList = auditableEntity.detectChanges();
		auditionTransactionContext.get().record(differenceList);
	}

	@Around("@annotation(org.silverbullit.auditlog.AuditionTransaction)")
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
