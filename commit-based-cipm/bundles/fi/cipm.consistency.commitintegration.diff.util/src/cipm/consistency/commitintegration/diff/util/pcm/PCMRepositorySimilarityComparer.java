package cipm.consistency.commitintegration.diff.util.pcm;

import org.eclipse.emf.ecore.EObject;
import org.splevo.jamopp.diffing.similarity.base.AbstractSimilarityComparer;

public class PCMRepositorySimilarityComparer extends AbstractSimilarityComparer {

	@Override
	public PCMRepositorySimilarityComparer clone(boolean checkStatementPosition) {
		var clone = new PCMRepositorySimilarityComparer();
		clone.setChecksStatementPositionOnDefault(checkStatementPosition);
		
		return clone;
	}

	@Override
	public Boolean checkSimilarityForResolvedAndSameType(EObject element1, EObject element2,
			boolean checkStatementPosition) {
		var clone = this.clone(checkStatementPosition);
		
		return new PCMRepositorySimilaritySwitch(clone).compare(element1, element2);
	}

}
