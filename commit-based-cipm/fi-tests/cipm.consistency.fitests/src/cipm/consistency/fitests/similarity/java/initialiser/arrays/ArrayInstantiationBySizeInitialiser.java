package cipm.consistency.fitests.similarity.java.initialiser.arrays;

import org.emftext.language.java.arrays.ArrayInstantiationBySize;
import org.emftext.language.java.arrays.ArraysFactory;

public class ArrayInstantiationBySizeInitialiser implements IArrayInstantiationBySizeInitialiser {
	@Override
	public IArrayInstantiationBySizeInitialiser newInitialiser() {
		return new ArrayInstantiationBySizeInitialiser();
	}

	@Override
	public ArrayInstantiationBySize instantiate() {
		return ArraysFactory.eINSTANCE.createArrayInstantiationBySize();
	}
}