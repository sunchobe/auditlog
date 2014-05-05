package org.silverbullit.auditlog;

import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Basic Entity-Class providing the necessary functionality to make changes of
 * JPA-Entities "automatically" detectable using the available JPA-callbacks.
 * 
 * @author Christian Ober
 */
@MappedSuperclass
public abstract class AuditableJpaEntity extends AuditableEntity {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LogManager.getLogger(AuditableJpaEntity.class);

	@PostLoad
	public void onPostLoad() {
		logger.debug("onPostLoad() called");
		this.saveCurrentEntity();
	}

	@PostPersist
	public void onPostPersist() {
		logger.debug("onPostPersist() called");
		this.detectAndRecordChanges();
	}

	@PostUpdate
	public void onPostUpdate() {
		logger.debug("onPostUpdate() called");
	}

	@PrePersist
	public void onPrePersist() {
		logger.debug("onPrePersist() called");
	}

	@PreUpdate
	public void onPreUpdate() {
		logger.debug("onPreUpdate() called");
		this.detectAndRecordChanges();
	}
}
