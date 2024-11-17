package coveragepac;





/**
 * Concrete implementation of {@link AbstractComposedSimilaritySwitchComparer}
 * for comparing Java elements.
 * 
 * @author atora
 */
public class JavaSimilarityComparer extends AbstractComposedSimilaritySwitchComparer {
	/**
	 * Constructs an instance with the given parameter.
	 * 
	 * @param st {@link ISimilarityToolbox} to which all incoming
	 *           {@link ISimilarityRequest} instances will be delegated to.
	 */
	public JavaSimilarityComparer(ISimilarityToolbox st) {
		super(st);
	}
}
