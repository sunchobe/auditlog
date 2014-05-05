package org.silverbullit.auditlog;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuditionTransactionContext {

	private static final Logger logger = LogManager.getLogger(AuditionTransactionContext.class);

	private final List<DifferenceList<String>> differenceList = new ArrayList<>();

	public void clear() {
		logger.debug("clearing {} elements", this.differenceList.size());
		this.differenceList.clear();
	}

	public List<DifferenceList<String>> getRecordedDifferences() {
		return this.differenceList;
	}

	public void record(final DifferenceList<String> differenceList) {
		logger.debug("recording: {}", differenceList);
		this.differenceList.add(differenceList);
	}
}
