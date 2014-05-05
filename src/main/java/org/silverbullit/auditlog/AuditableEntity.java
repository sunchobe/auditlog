package org.silverbullit.auditlog;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.apache.commons.lang3.SerializationUtils;

@MappedSuperclass
public abstract class AuditableEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private static List<Field> getAllFields(List<Field> fields, final Class<?> type) {
		for (final Field field : type.getDeclaredFields()) {
			fields.add(field);
		}

		if (type.getSuperclass() != null) {
			fields = getAllFields(fields, type.getSuperclass());
		}

		return fields;
	}

	@Id
	@GeneratedValue
	protected Long id;

	@Version
	private long version;

	@Transient
	private AuditableEntity savedEntity;

	public DifferenceList<String> detectChanges() {
		DifferenceType differenceType = DifferenceType.CREATION;
		if (this.savedEntity != null) {
			differenceType = DifferenceType.UPDATE;
		}
		final DifferenceList<String> differenceSet = new DifferenceList<String>(differenceType, this.getClass().getSimpleName());
		final List<Field> fields = getAllFields(new ArrayList<Field>(), this.getClass());
		for (final Field field : fields) {
			if (field.isAnnotationPresent(Auditable.class)) {
				try {
					if (field.getModifiers() == Modifier.PRIVATE) {
						field.setAccessible(true);
					}

					Difference<Object> difference = null;
					switch (differenceType) {
					case CREATION:
						difference = new Difference<Object>(field.getName(), null, field.get(this));
						break;
					case UPDATE:
						difference = new Difference<Object>(field.getName(), field.get(this.savedEntity), field.get(this));
						break;
					}

					differenceSet.add(difference);

				} catch (final Exception e) {
					e.printStackTrace();
				}
			}
		}
		return differenceSet;
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
