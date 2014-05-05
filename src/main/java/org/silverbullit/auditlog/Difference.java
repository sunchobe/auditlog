package org.silverbullit.auditlog;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Difference<T> {

	private String identifier;

	private T oldValue;

	private T newValue;

	public Difference(final String identifier, final T oldValue, final T newValue) {
		this.identifier = identifier;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	public String getIdentifier() {
		return this.identifier;
	}

	public T getNewValue() {
		return this.newValue;
	}

	public T getOldValue() {
		return this.oldValue;
	}

	public void setIdentifier(final String identifier) {
		this.identifier = identifier;
	}

	public void setNewValue(final T newValue) {
		this.newValue = newValue;
	}

	public void setOldValue(final T oldValue) {
		this.oldValue = oldValue;
	}

	@Override
	public String toString() {
		final ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);
		builder.append("identifier", this.identifier);
		builder.append("oldValue", this.oldValue);
		builder.append("newValue", this.newValue);
		return builder.toString();
	}
}
