package cipm.consistency.fitests.similarity.java.eobject.initialiser.arrays;

import org.emftext.language.java.arrays.ArraysFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

import org.emftext.language.java.arrays.ArrayDimension;

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