package cipm.consistency.fitests.similarity.base.dummy;

import coveragepac.ISimilarityRequest;
import coveragepac.ISimilarityRequestHandler;

/**
 * An exemplary custom handler for {@link ReferenceCheckRequest}.
 * 
 * @author Alp Torac Genc
 */
public class ReferenceCheckRequestHandler implements ISimilarityRequestHandler {
	@Override
	public Object handleSimilarityRequest(ISimilarityRequest req) {
		var castedReq = (ReferenceCheckRequest) req;
		var elems = (Object[]) castedReq.getParams();
		
		var elem1 = elems[0];
		var elem2 = elems[1];
		
		return elem1 == elem2;
	}

	@Override
	public boolean canHandleSimilarityRequest(Class<? extends ISimilarityRequest> reqClass) {
		return reqClass.equals(ReferenceCheckRequest.class);
	}
}