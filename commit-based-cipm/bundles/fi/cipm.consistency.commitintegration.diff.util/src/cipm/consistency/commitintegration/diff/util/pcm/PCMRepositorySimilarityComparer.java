package cipm.consistency.commitintegration.diff.util.pcm;

import org.splevo.jamopp.diffing.similarity.base.ecore.AbstractComposedSimilaritySwitchComparer;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityToolbox;

public class PCMRepositorySimilarityComparer extends AbstractComposedSimilaritySwitchComparer {
	public PCMRepositorySimilarityComparer(ISimilarityToolbox st) {
		super(st);
	}
}
