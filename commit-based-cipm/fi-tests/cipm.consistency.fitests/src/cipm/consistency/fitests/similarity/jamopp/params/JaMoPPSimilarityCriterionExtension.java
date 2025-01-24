package cipm.consistency.fitests.similarity.jamopp.params;

/**
 * An enum, which contains constants that can be used for adding similarity
 * entries associated with aspects of objects that have to be derived and/or do
 * not have a properly corresponding attribute.
 * 
 * @author Alp Torac Genc
 * 
 * @see {@link JaMoPPSimilarityValues}
 */
public enum JaMoPPSimilarityCriterionExtension {
	/**
	 * Stands for the value of {@code eobject.eContainer()}.
	 */
	ECONTAINER,
	/**
	 * Stands for the position of {@link Statement} instances within their
	 * container.
	 */
	STATEMENT_POSITION
}
