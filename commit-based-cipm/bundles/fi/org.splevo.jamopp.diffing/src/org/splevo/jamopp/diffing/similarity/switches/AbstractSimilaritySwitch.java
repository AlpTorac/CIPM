package org.splevo.jamopp.diffing.similarity.switches;

import org.apache.log4j.Logger;

import com.google.common.base.Strings;

public abstract class AbstractSimilaritySwitch implements ISimilaritySwitch {
	/**
	 * The secondary, more detailed logger
	 */
	private Logger fiLogger = Logger.getLogger("fi." + this.getClass().getSimpleName());

	protected Logger getLogger() {
		return this.fiLogger;
	}

	public void logComparison(String subject1, String subject2) {
		this.getLogger().info("Comparing " + this.getComparisonSubjectTypeString() + "s (1 vs 2): "
				+ Strings.nullToEmpty(subject1) + " vs " + Strings.nullToEmpty(subject2));
	}

	public void logResult(boolean result) {
		this.getLogger().info(this.getComparisonSubjectTypeString() + "s similar: " + result);
	}

	public void logMessage(String msg) {
		this.getLogger().info(msg + " (while comparing" + this.getComparisonSubjectTypeString() + ")");
	}
}
