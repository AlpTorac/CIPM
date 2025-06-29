package cipm.consistency.fitests.similarity.jamopp.parser;

public enum GeneralTimeMeasurementTag implements ITimeMeasurementTag {
	/**
	 * 
	 */
	DISCOVER_MODEL_RESOURCES,
	/**
	 * 
	 */
	PARSE_MODEL_RESOURCE,
	/**
	 * 
	 */
	LOAD_MODEL_RESOURCE,
	/**
	 * 
	 */
	UNLOAD_MODEL_RESOURCE,
	/**
	 * 
	 */
	SAVE_MODEL_RESOURCE,
	/**
	 * 
	 */
	DELETE_MODEL_RESOURCE,
	/**
	 * 
	 */
	MODEL_RESOURCE_CACHE_ACCESS,

	/**
	 * 
	 */
	TEST_BEFOREEACH,
	/**
	 * 
	 */
	TEST_AFTEREACH,
	/**
	 * 
	 */
	DYNAMIC_TEST_CREATION,
	/**
	 * 
	 */
	TEST_OVERHEAD,

	/**
	 * 
	 */
	SIMILARITY_CHECKING,
	/**
	 * 
	 */
	MODEL_RESOURCE_COMPARISON,
	/**
	 * 
	 */
	EXPECTED_SIMILARITY_RESULT_COMPUTATION

	;
}