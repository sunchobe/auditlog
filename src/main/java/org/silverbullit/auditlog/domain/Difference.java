package org.silverbullit.auditlog.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Bean containing the information that specify a difference between two values.
 * 
 * @author Christian Ober
 * 
 * @param <T>
 *          the generic type of the value this container keeps the changes for.
 */
public class Difference<T> {

	/**
	 * The identifier. Can be used to identify the difference (e.g. the name of
	 * the field).
	 */
	private String identifier;

	/**
	 * the value before the change happened
	 */
	private T oldValue;

	/**
	 * the value after the change happened
	 */
	private T newValue;

	/**
	 * Creates an object of the class by setting the necessary fields.
	 * 
	 * @param identifier
	 *          the identifier
	 * @param oldValue
	 *          the old value
	 * @param newValue
	 *          the new value
	 */
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
