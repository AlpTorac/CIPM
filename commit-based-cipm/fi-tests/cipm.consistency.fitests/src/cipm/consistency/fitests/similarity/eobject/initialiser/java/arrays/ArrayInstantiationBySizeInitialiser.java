package cipm.consistency.fitests.similarity.eobject.initialiser.java.arrays;

import org.emftext.language.java.arrays.ArrayInstantiationBySize;
import org.emftext.language.java.arrays.ArraysFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class ArrayInstantiationBySizeInitialiser extends AbstractInitialiserBase
		implements IArrayInstantiationBySizeInitialiser {
	@Override
	public IArrayInstantiationBySizeInitialiser newInitialiser() {
		return new ArrayInstantiationBySizeInitialiser();
	}

	@Override
	public ArrayInstantiationBySize instantiate() {
		return ArraysFactory.eINSTANCE.createArrayInstantiationBySize();
	}
}