package cipm.consistency.commitintegration.diff.util.pcm.requests;

import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequest;

public class NewPCMRepositorySimilaritySwitchRequest implements ISimilarityRequest {
	private boolean checkStatementPosition;
	
	public NewPCMRepositorySimilaritySwitchRequest(boolean checkStatementPosition) {
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
