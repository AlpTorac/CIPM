package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.arrays.ArrayInstantiationByValuesTyped;
import org.emftext.language.java.arrays.ArraysFactory;

import cipm.consistency.fitests.similarity.java.initialiser.IArrayInstantiationByValuesTypedInitialiser;

public class ArrayInstantiationByValuesTypedInitialiser implements IArrayInstantiationByValuesTypedInitialiser {
	@Override
	public IArrayInstantiationByValuesTypedInitialiser newInitialiser() {
		return new ArrayInstantiationByValuesTypedInitialiser();
	}

	@Override
	public ArrayInstantiationByValuesTyped instantiate() {
		return ArraysFactory.eINSTANCE.createArrayInstantiationByValuesTyped();
	}
}