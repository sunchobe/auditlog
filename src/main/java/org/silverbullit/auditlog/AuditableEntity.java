package org.silverbullit.auditlog;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

import org.apache.commons.lang3.SerializationUtils;

@MappedSuperclass
public abstract class AuditableEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Transient
	private AuditableEntity savedEntity;

	protected AuditableEntity getSavedEntity() {
		return this.savedEntity;
	}

	@PostLoad
	public void onPostLoad() {
		this.savedEntity = SerializationUtils.clone(this);
	}

	@PostPersist
	public void onPostPersist() {
		AuditionTransactionHandler.detectAndRecordChanges(this);
	}

	@PostUpdate
	public void onPostUpdate() {

	}

	@PrePersist
	public void onPrePersist() {

	}

	@PreUpdate
	public void onPreUpdate() {
		AuditionTransactionHandler.detectAndRecordChanges(this);
	}
}
