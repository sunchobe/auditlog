package org.silverbullit.auditlog;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.silverbullit.auditlog.domain.DifferenceList;

public class AuditionTransactionLoggingHandler extends AuditionTransactionHandler {

	private static final Logger logger = LogManager.getLogger(AuditionTransactionLoggingHandler.class);

	@Override
	void handle(final List<DifferenceList<String>> collectedDifferences) {
		for (final DifferenceList<String> recordedDifference : collectedDifferences) {
			logger.info("recorded differences: {}", recordedDifference);
		}
	}

}
