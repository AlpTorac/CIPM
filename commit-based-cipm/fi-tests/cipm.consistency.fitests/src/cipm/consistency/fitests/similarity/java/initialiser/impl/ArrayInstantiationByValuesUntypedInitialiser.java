package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.arrays.ArrayInstantiationByValuesUntyped;
import org.emftext.language.java.arrays.ArraysFactory;

import cipm.consistency.fitests.similarity.java.initialiser.IArrayInstantiationByValuesUntypedInitialiser;

public class ArrayInstantiationByValuesUntypedInitialiser implements IArrayInstantiationByValuesUntypedInitialiser {
	@Override
	public IArrayInstantiationByValuesUntypedInitialiser newInitialiser() {
		return new ArrayInstantiationByValuesUntypedInitialiser();
	}

	@Override
	public ArrayInstantiationByValuesUntyped instantiate() {
		return ArraysFactory.eINSTANCE.createArrayInstantiationByValuesUntyped();
	}
}