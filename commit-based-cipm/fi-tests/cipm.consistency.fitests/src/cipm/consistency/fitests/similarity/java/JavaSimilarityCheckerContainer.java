package cipm.consistency.fitests.similarity.java;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityChecker;

public class JavaSimilarityCheckerContainer extends AbstractSimilarityCheckerContainer {
	private ISimilarityChecker sc;
	
	public ISimilarityChecker getSimilarityChecker() {
		if (this.sc == null) {
			this.sc = this.getSimilarityCheckerProvider().createSC();
		}

		return this.sc;
	}
	
	@Override
	public void resetSimilarityChecker() {
		this.sc = this.getSimilarityChecker();
	}

	/**
	 * Delegates similarity checking to the underlying {@link ISimilarityChecker}.
	 */
	public Boolean isSimilar(EObject element1, EObject element2) {
		return this.getSimilarityChecker().isSimilar(element1, element2);
	}

	/**
	 * Delegates similarity checking to the underlying {@link ISimilarityChecker}.
	 */
	@SuppressWarnings("unchecked")
	public Boolean areSimilar(Collection<? extends EObject> elements1,
			Collection<? extends EObject> elements2) {
		return this.getSimilarityChecker().areSimilar((Collection<Object>) elements1, (Collection<Object>) elements2);
	}
}
