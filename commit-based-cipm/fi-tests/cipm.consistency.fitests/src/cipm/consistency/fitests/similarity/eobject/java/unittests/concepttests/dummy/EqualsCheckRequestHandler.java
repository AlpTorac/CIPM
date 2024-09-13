package cipm.consistency.fitests.similarity.eobject.java.unittests.concepttests.dummy;

import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequest;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequestHandler;

public class EqualsCheckRequestHandler implements ISimilarityRequestHandler {
	@Override
	public Object handleSimilarityRequest(ISimilarityRequest req) {
		var castedReq = (EqualsCheckRequest) req;
		var elems = (Object[]) castedReq.getParams();
		
		var elem1 = elems[0];
		var elem2 = elems[1];
		
		return elem1.equals(elem2);
	}

	@Override
	public boolean canHandleSimilarityRequest(Class<? extends ISimilarityRequest> reqClass) {
		return reqClass.equals(EqualsCheckRequest.class);
	}
}
