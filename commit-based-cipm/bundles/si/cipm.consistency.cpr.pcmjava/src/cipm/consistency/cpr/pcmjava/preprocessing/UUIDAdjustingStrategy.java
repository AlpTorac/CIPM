package cipm.consistency.cpr.pcmjava.preprocessing;

import org.eclipse.emf.ecore.EStructuralFeature;

public class UUIDAdjustingStrategy extends IDAdjustingStrategy {
	@Override
	public String setAsValueOfFeature(String currentID, EStructuralFeature toBeContainingSingleValuedFeature) {
		return currentID;
	}

	@Override
	public String adjustIndicesOf(String currentID, int[] newIndices) {
		return currentID;
	}

	@Override
	public String adjustIndexOf(String currentID, int indexNumber, int newIndex) {
		return currentID;
	}

	@Override
	public String addIndex(String currentID, int indexValue) {
		return currentID;
	}

	@Override
	public String insertIntoFeature(String currentID, EStructuralFeature toBeContainingManyValuedFeature,
			int indexValue) {
		return currentID;
	}
}
