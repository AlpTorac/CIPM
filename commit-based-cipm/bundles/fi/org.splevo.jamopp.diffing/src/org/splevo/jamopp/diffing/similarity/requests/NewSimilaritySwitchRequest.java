package org.splevo.jamopp.diffing.similarity.requests;

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
		return this.checkStatementPosition ? Boolean.TRUE : Boolean.FALSE;
	}
}
