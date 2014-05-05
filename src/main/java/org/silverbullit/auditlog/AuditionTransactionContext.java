package org.silverbullit.auditlog;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.silverbullit.auditlog.domain.DifferenceList;

/**
 * The AuditionTransactionContext keeps track of the changes detected within an
 * AuditionTransaction.
 * 
 * @author Christian Ober
 */
public class AuditionTransactionContext {

	private static final Logger logger = LogManager.getLogger(AuditionTransactionContext.class);

	/**
	 * the list of differences of the current AuditionTransaction
	 */
	private final List<DifferenceList<String>> differenceList = new ArrayList<>();

	/**
	 * removes all detected changes from the context.
	 */
	public void clear() {
		logger.debug("clearing {} elements", this.differenceList.size());
		this.differenceList.clear();
	}

	public List<DifferenceList<String>> getRecordedDifferences() {
		return this.differenceList;
	}

	/**
	 * records a list of differences that belong together.
	 * 
	 * @param differenceList
	 *          the list of differences.
	 */
	public void record(final DifferenceList<String> differenceList) {
		logger.debug("recording: {}", differenceList);
		this.differenceList.add(differenceList);
	}
}
