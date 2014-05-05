package org.silverbullit.auditlog;

import java.util.ArrayList;
import java.util.Date;

public class DifferenceSet<T> extends ArrayList<Difference<?>> {

	private static final long serialVersionUID = 1L;

	private final T identifier;

	private final Date changeDate;

	private final DifferenceType differenceType;

	public DifferenceSet(final DifferenceType differenceType, final T identifier) {
		this.changeDate = new Date();
		this.identifier = identifier;
		this.differenceType = differenceType;
	}

	public Date getChangeDate() {
		return this.changeDate;
	}

	public T getIdentifier() {
		return this.identifier;
	}

	public DifferenceType getDifferenceType() {
		return differenceType;
	}

}
