package org.silverbullit.auditlog;

import java.io.Serializable;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Basic Entity-Class providing the necessary functionality to make changes
 * detectable.
 * 
 * @author Christian Ober
 */
public abstract class AuditableEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LogManager.getLogger(AuditableEntity.class);

	/**
	 * the entity captured directly after loading from the database
	 */
	private AuditableEntity savedEntity;

	/**
	 * detects the available changes and puts them into the context of the
	 * AuditionTransactionHandler.
	 */
	protected void detectAndRecordChanges() {
		logger.debug("detecting and recording changes");
		AuditionTransactionHandler.detectAndRecordChanges(this);
	}

	/**
	 * retrieves the entity kept directly after loading.
	 * 
	 * @return the initially stored entity
	 */
	protected AuditableEntity getSavedEntity() {
		return this.savedEntity;
	}

	/**
	 * keeps the current entity values (should be called directly after loading an
	 * entity from a data store)
	 */
	protected void saveCurrentEntity() {
		logger.debug("saving current entity");
		this.savedEntity = SerializationUtils.clone(this);
	}

}
