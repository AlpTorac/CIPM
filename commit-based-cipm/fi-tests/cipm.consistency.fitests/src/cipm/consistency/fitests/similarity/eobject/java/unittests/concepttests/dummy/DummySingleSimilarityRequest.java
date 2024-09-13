package cipm.consistency.fitests.similarity.eobject.java.unittests.concepttests.dummy;

import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequest;

public class DummySingleSimilarityRequest implements ISimilarityRequest {
	private Object elem1;
	private Object elem2;
	
	public DummySingleSimilarityRequest(Object elem1, Object elem2) {
		this.elem1 = elem1;
		this.elem2 = elem2;
	}
	
	@Override
	public Object getParams() {
		return new Object[] {elem1, elem2};
	}
}
