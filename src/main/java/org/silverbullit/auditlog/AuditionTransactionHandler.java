package org.silverbullit.auditlog;

public class AuditionTransactionHandler {

	private static final ThreadLocal<AuditionTransactionContext> auditionTransactionContext = new ThreadLocal<>();
	
	public static void detectChanges(AuditableEntity savedEntity,
			AuditableEntity auditableEntity) {
		// TODO Auto-generated method stub
		
	}

	public static void detectNew(AuditableEntity auditableEntity) {
		// TODO Auto-generated method stub
		
	}
}
