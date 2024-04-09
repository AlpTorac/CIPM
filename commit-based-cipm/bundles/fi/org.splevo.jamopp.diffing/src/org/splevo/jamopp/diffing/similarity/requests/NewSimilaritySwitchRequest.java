package org.splevo.jamopp.diffing.similarity.requests;

import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequest;

public class NewSimilaritySwitchRequest implements ISimilarityRequest {
	private boolean checkStatementPosition;
	
	public NewSimilaritySwitchRequest(boolean checkStatementPosition) {
		this.checkStatementPosition = checkStatementPosition;
	}
	
	/**
	 * @return {@link #checkStatementPosition}
	 */
	@Override
	public Boolean getParams() {
		return this.checkStatementPosition;
	}
}
