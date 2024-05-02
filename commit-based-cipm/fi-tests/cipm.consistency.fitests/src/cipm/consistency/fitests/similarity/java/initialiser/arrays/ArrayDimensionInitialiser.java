package cipm.consistency.fitests.similarity.java.initialiser.arrays;

import org.emftext.language.java.arrays.ArraysFactory;
import org.emftext.language.java.arrays.ArrayDimension;

public class ArrayDimensionInitialiser implements IArrayDimensionInitialiser {
	@Override
	public IArrayDimensionInitialiser newInitialiser() {
		return new ArrayDimensionInitialiser();
	}

	@Override
	public ArrayDimension instantiate() {
		return ArraysFactory.eINSTANCE.createArrayDimension();
	}
}