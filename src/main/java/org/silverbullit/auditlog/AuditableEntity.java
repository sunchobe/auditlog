package org.silverbullit.auditlog;

import java.io.Serializable;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PreUpdate;

import org.apache.commons.lang3.SerializationUtils;

public abstract class AuditableEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private AuditableEntity savedEntity;
	
	@PostLoad
	public void onPostLoad() {
		savedEntity = SerializationUtils.clone(this);
	}
	
	@PreUpdate
	public void onPreUpdate() {
		AuditionTransactionHandler.detectChanges(savedEntity, this);
	}
	
	@PostPersist
	public void onPostPersist() {
		AuditionTransactionHandler.detectNew(this);
	}

}
