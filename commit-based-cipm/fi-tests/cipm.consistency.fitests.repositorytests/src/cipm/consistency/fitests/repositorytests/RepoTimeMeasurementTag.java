package cipm.consistency.fitests.repositorytests;

import cipm.consistency.fitests.similarity.jamopp.parser.ITimeMeasurementTag;

public enum RepoTimeMeasurementTag implements ITimeMeasurementTag {
	/**
	 * 
	 */
	CLONE_REPOSITORY,
	/**
	 * 
	 */
	CHECKOUT_TO_COMMIT,
	/**
	 * 
	 */
	CLOSE_REPOSITORY,
	/**
	 * 
	 */
	DELETE_LOCAL_REPO_CLONE,

	/**
	 * 
	 */
	LOAD_EXPECTED_SIMILARITY_RESULTS,
	/**
	 * 
	 */
	SAVE_EXPECTED_SIMILARITY_RESULTS,

	;
}
