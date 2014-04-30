package org.silverbullit.auditlog;

public class AuditionTransactionHandler {

	private static final ThreadLocal<AuditionTransactionContext> auditionTransactionContext = new ThreadLocal<>();
	
	public static AuditionTransactionContext getContext() {
		return auditionTransactionContext.get();
	}
}
