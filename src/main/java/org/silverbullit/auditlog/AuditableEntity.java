package org.silverbullit.auditlog;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import org.apache.commons.lang3.SerializationUtils;

@MappedSuperclass
public abstract class AuditableEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;

	@Version
	private long version;

	private AuditableEntity savedEntity;

	@PostLoad
	public void onPostLoad() {
		System.out.println("postload");
		savedEntity = SerializationUtils.clone(this);
	}

	@PreUpdate
	public void onPreUpdate() {
		System.out.println("preupdate");
		AuditionTransactionHandler.detectChanges(savedEntity, this);
	}

	@PostPersist
	public void onPostPersist() {
		System.out.println("postpersist");
		AuditionTransactionHandler.detectNew(this);
	}

}
