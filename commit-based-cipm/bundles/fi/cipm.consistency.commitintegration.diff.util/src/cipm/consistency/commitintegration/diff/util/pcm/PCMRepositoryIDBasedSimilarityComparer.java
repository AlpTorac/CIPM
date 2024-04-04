package cipm.consistency.commitintegration.diff.util.pcm;

import org.eclipse.emf.ecore.EObject;

import de.uka.ipd.sdq.identifier.Identifier;

public class PCMRepositoryIDBasedSimilarityComparer extends PCMRepositorySimilarityComparer {

	@Override
	public PCMRepositoryIDBasedSimilarityComparer clone(boolean checkStatementPosition) {
		var clone = new PCMRepositoryIDBasedSimilarityComparer();
		clone.setChecksStatementPositionOnDefault(checkStatementPosition);
		
		return clone;
	}
	
	@Override
	public Boolean checkSimilarityForResolvedAndSameType(EObject element1, EObject element2,
			boolean checkStatementPosition) {
		if (element1 instanceof Identifier) {
			if (element2 instanceof Identifier) {
				Identifier id1 = (Identifier) element1;
				Identifier id2 = (Identifier) element2;
				return id1.getId().equals(id2.getId());
			}
			return Boolean.FALSE;
		}
		return super.checkSimilarityForResolvedAndSameType(element1, element2, checkStatementPosition);
	}
}
