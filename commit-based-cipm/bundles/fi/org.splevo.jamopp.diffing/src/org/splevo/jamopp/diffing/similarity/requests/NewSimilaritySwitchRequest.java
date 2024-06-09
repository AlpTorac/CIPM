package org.splevo.jamopp.diffing.similarity.requests;

import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequest;

/**
 * An {@link ISimilarityRequest}, which contains a flag that denotes,
 * whether the new similarity switch created by processing this request should
 * care about positions of statements while computing similarity.
 * 
 * @author atora
 */
public class NewSimilaritySwitchRequest implements ISimilarityRequest {
	/**
	 * The flag that denotes, whether the resulting new similarity switch should
	 * care about positions of statements while computing similarity.
	 */
	private boolean checkStatementPosition;

	/**
	 * Constructs an instance with the given parameter.
	 * 
	 * @param checkStatementPosition {@link #checkStatementPosition}
	 */
	public NewSimilaritySwitchRequest(boolean checkStatementPosition) {
		this.checkStatementPosition = checkStatementPosition;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @return {@link #checkStatementPosition}
	 */
	@Override
	public Object getParams() {
		return this.checkStatementPosition;
	}
}
