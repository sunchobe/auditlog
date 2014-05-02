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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@MappedSuperclass
public abstract class AuditableEntity implements Serializable {

	private static final Logger logger = LogManager.getLogger(AuditableEntity.class);
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	protected long id;

	@Version
	private long version;

	private AuditableEntity savedEntity;

	@PostLoad
	public void onPostLoad() {
		logger.debug("postload");
		savedEntity = SerializationUtils.clone(this);
	}

	@PreUpdate
	public void onPreUpdate() {
		logger.debug("preupdate");
		AuditionTransactionHandler.detectChanges(savedEntity, this);
	}

	@PostPersist
	public void onPostPersist() {
		logger.debug("postpersist");
		AuditionTransactionHandler.detectNew(this);
	}

}
