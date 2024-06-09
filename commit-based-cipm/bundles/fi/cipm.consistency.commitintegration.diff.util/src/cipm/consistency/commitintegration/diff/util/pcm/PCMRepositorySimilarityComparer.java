package cipm.consistency.commitintegration.diff.util.pcm;

import org.splevo.jamopp.diffing.similarity.base.ecore.AbstractComposedSimilaritySwitchComparer;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityToolbox;

/**
 * Concrete implementation of {@link AbstractComposedSimilaritySwitchComparer} for
 * for comparing Palladio Component Model (PCM) repositories.
 * 
 * @author atora
 */
public class PCMRepositorySimilarityComparer extends AbstractComposedSimilaritySwitchComparer {
	/**
	 * @see {@link AbstractComposedSimilaritySwitchComparer#AbstractComposedSimilaritySwitchComparer(ISimilarityToolbox)}
	 */
	public PCMRepositorySimilarityComparer(ISimilarityToolbox st) {
		super(st);
	}
}
