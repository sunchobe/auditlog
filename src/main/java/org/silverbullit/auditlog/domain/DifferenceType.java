package org.silverbullit.auditlog.domain;

/**
 * The available types of differences.
 * 
 * @author Christian Ober
 */
public enum DifferenceType {

	/**
	 * if an auditable entity has been newly created
	 */
	CREATION,

	/**
	 * if an auditable entity has been changed/updated
	 */
	UPDATE,

	/**
	 * if an auditable entity has been removed/deleted
	 */
	REMOVAL;
}
