package cipm.consistency.fitests.similarity.base.dummy;

import coveragepac.AbstractSimilarityComparer;
import coveragepac.ISimilarityToolbox;

/**
 * A minimal similarity comparer implementation. <br>
 * <br>
 * As a layer of indirection between similarity checker and other internal
 * constructs, real similarity comparers may contain various utility methods and
 * delegation methods, which would otherwise have to be in similarity checker
 * and make it bloated.
 * 
 * @author Alp Torac Genc
 */
public class DummySimilarityComparer extends AbstractSimilarityComparer {
	public DummySimilarityComparer(ISimilarityToolbox st) {
		super(st);
	}
}
