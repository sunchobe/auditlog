package org.silverbullit.auditlog;

import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class DifferenceList<T> extends ArrayList<Difference<?>> {

	private static final long serialVersionUID = 1L;

	private final T identifier;

	private final Date changeDate;

	private final DifferenceType differenceType;

	public DifferenceList(final DifferenceType differenceType, final T identifier) {
		this.changeDate = new Date();
		this.identifier = identifier;
		this.differenceType = differenceType;
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
		for (final Difference<?> difference : this) {
			builder.append("difference", difference);
		}

		return builder.toString();
	}
}
