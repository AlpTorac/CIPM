package cipm.consistency.fitests.similarity.eobject.java.unittests.concepttests.dummy;

import java.util.Collection;

import org.splevo.jamopp.diffing.similarity.base.AbstractSimilarityChecker;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityComparer;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityToolbox;

public class DummySimilarityChecker extends AbstractSimilarityChecker {
	public DummySimilarityChecker(ISimilarityToolbox st) {
		super(st);
	}

	@Override
	public Boolean isSimilar(Object element1, Object element2) {
		return (Boolean) this.handleSimilarityRequest(new DummySingleSimilarityRequest(element1,
				element2));
	}

	@Override
	public Boolean areSimilar(Collection<Object> elements1, Collection<Object> elements2) {
		if (elements1 == elements2) return Boolean.TRUE;
		if (elements1 == null || elements2 == null) return Boolean.FALSE;
		if (elements1.size() != elements2.size()) return Boolean.FALSE;
		
		var result = true;
		var iter1 = elements1.iterator();
		var iter2 = elements2.iterator();
		
		for (int i = 0; i < elements1.size(); i++) {
			var cResult = this.isSimilar(iter1.next(), iter2.next());
			
			if (cResult == null) return null;
			
			result = result && cResult.booleanValue();
		}
		
		return Boolean.valueOf(result);
	}

	@Override
	protected ISimilarityComparer createSimilarityComparer(ISimilarityToolbox st) {
		return new DummySimilarityComparer(st);
	}
}
