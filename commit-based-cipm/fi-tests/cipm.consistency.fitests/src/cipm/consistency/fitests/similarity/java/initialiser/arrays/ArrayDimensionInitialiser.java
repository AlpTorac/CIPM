package cipm.consistency.fitests.similarity.java.initialiser.arrays;

import org.emftext.language.java.arrays.ArraysFactory;
import org.emftext.language.java.arrays.ArrayDimension;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class ArrayDimensionInitialiser extends AbstractInitialiserBase implements IArrayDimensionInitialiser {
	@Override
	public IArrayDimensionInitialiser newInitialiser() {
		return new ArrayDimensionInitialiser();
	}

	@Override
	public ArrayDimension instantiate() {
		return ArraysFactory.eINSTANCE.createArrayDimension();
	}
}