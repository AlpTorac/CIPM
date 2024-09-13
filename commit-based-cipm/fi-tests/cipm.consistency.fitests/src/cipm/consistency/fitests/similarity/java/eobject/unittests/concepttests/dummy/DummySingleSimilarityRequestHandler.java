package cipm.consistency.fitests.similarity.java.eobject.unittests.concepttests.dummy;

import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequest;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequestHandler;

public class DummySingleSimilarityRequestHandler implements ISimilarityRequestHandler {
	private ISimilarityRequestHandler srh;
	
	public DummySingleSimilarityRequestHandler(ISimilarityRequestHandler srh) {
		this.srh = srh;
	}
	
	@Override
	public Object handleSimilarityRequest(ISimilarityRequest req) {
		var castedReq = (DummySingleSimilarityRequest) req;
		var elems = (Object[]) castedReq.getParams();
		var elem1 = elems[0];
		var elem2 = elems[1];
		
		if ((Boolean) srh.handleSimilarityRequest(new ReferenceCheckRequest(elem1, elem2))) {
			return Boolean.TRUE;
		}
		else if (elem1 == null || elem2 == null) {
			return Boolean.FALSE;
		}
		
		return srh.handleSimilarityRequest(new EqualsCheckRequest(elem1, elem2));
	}

	@Override
	public boolean canHandleSimilarityRequest(Class<? extends ISimilarityRequest> reqClass) {
		return reqClass.equals(DummySingleSimilarityRequest.class);
	}
}
