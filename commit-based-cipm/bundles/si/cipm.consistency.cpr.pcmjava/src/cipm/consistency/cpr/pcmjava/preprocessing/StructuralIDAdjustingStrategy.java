package cipm.consistency.cpr.pcmjava.preprocessing;

import org.eclipse.emf.ecore.EStructuralFeature;

public class StructuralIDAdjustingStrategy extends IDAdjustingStrategy {
	@Override
	public String setAsValueOfFeature(String currentID, EStructuralFeature toBeContainingSingleValuedFeature) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String adjustIndicesOf(String currentID, int[] newIndices) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addIndex(String currentID, int indexValue) {
		return currentID + "/" + indexValue;
	}

	@Override
	public String adjustIndexOf(String currentID, int indexNumber, int newIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String insertIntoFeature(String currentID, EStructuralFeature toBeContainingManyValuedFeature,
			int indexValue) {
		// TODO Auto-generated method stub
		return null;
	}

}
