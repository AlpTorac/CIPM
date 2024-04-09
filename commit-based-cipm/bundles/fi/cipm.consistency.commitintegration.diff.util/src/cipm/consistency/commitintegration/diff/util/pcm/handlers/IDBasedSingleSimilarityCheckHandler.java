package cipm.consistency.commitintegration.diff.util.pcm.handlers;

import org.eclipse.emf.ecore.EObject;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequest;
import org.splevo.jamopp.diffing.similarity.base.ecore.SingleSimilarityCheckHandler;
import org.splevo.jamopp.diffing.similarity.base.ecore.SingleSimilarityCheckRequest;

import de.uka.ipd.sdq.identifier.Identifier;

public class IDBasedSingleSimilarityCheckHandler extends SingleSimilarityCheckHandler {
	@Override
	public Boolean handleSimilarityRequest(ISimilarityRequest req) {
		SingleSimilarityCheckRequest castedR = (SingleSimilarityCheckRequest) req;
		var params = castedR.getParams();
		EObject element1 = (EObject) params[0];
		EObject element2 = (EObject) params[1];

		if (element1 instanceof Identifier) {
			if (element2 instanceof Identifier) {
				Identifier id1 = (Identifier) element1;
				Identifier id2 = (Identifier) element2;
				return id1.getId().equals(id2.getId());
			}
			return Boolean.FALSE;
		}
		
		return super.handleSimilarityRequest(req);
	}
}
