package org.silverbullit.auditlog.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Container to group a list of differences.
 * 
 * @author Christian Ober
 * 
 * @param <T>
 *          the generic type of the identifier usable to group the differences
 */
public class DifferenceList<T> {

	/**
	 * the inner list of differences
	 */
	private final List<Difference<?>> differenceList = new ArrayList<>();

	/**
	 * the identifier usable to identify the list of differences that belong
	 * together
	 */
	private final T identifier;

	/**
	 * the date of change/creation
	 */
	private final Date changeDate;

	/**
	 * the type of the difference
	 */
	private final DifferenceType differenceType;

	/**
	 * Creates an object of the class.
	 * 
	 * @param differenceType
	 *          the type of the differences to be grouped in the list
	 * @param identifier
	 *          the identifier
	 */
	public DifferenceList(final DifferenceType differenceType, final T identifier) {
		this.changeDate = new Date();
		this.identifier = identifier;
		this.differenceType = differenceType;
	}

	/**
	 * Adds a Difference to the inner list of Differences.
	 * 
	 * @param difference
	 *          the difference to add
	 */
	public void add(final Difference<Object> difference) {
		this.differenceList.add(difference);
	}

	public Date getChangeDate() {
		return this.changeDate;
	}

	public DifferenceType getDifferenceType() {
		return this.differenceType;
	}

	public T getIdentifier() {
		return this.identifier;
	}

	@Override
	public String toString() {
		final ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);

		builder.append("identifier", this.identifier);
		builder.append("changeDate", this.changeDate);
		builder.append("differenceType", this.differenceType);
		for (final Difference<?> difference : this.differenceList) {
			builder.append("difference", difference);
		}

		return builder.toString();
	}
}
