package cipm.consistency.commitintegration.diff.util.pcm;

import org.eclipse.emf.ecore.EObject;
import org.splevo.jamopp.diffing.similarity.base.AbstractSimilarityChecker;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityToolbox;
import org.splevo.jamopp.diffing.similarity.base.ecore.SingleSimilarityCheckRequest;

import cipm.consistency.commitintegration.diff.util.pcm.requests.NewPCMRepositorySimilaritySwitchRequest;

/**
 * A SimilarityChecker for PCM repository models.
 * 
 * @author Martin Armbruster
 */
public class PCMRepositorySimilarityChecker extends AbstractSimilarityChecker {
	public PCMRepositorySimilarityChecker(ISimilarityToolbox st) {
		super(st);
	}

	@Override
	protected PCMRepositorySimilarityComparer createSimilarityComparer(ISimilarityToolbox st) {
		return new PCMRepositorySimilarityComparer(st);
	}

	@Override
	public Boolean isSimilar(Object element1, Object element2) {
		IPCMRepositorySimilaritySwitch ss = (IPCMRepositorySimilaritySwitch) this.getSimilarityComparer()
				.handleSimilarityRequest(new NewPCMRepositorySimilaritySwitchRequest(true));
		
		return (Boolean) ss.handleSimilarityRequest(new SingleSimilarityCheckRequest((EObject) element1, (EObject) element2, ss));
	}
}
