package org.silverbullit.auditlog;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class BaseEntity extends AuditableJpaEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	protected Long id;

	@Version
	private long version;

}
