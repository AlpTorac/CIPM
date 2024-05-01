package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.arrays.ArraySelector;
import org.emftext.language.java.arrays.ArraysFactory;

import cipm.consistency.fitests.similarity.java.initialiser.IArraySelectorInitialiser;

public class ArraySelectorInitialiser implements IArraySelectorInitialiser {
	@Override
	public IArraySelectorInitialiser newInitialiser() {
		return new ArraySelectorInitialiser();
	}

	@Override
	public ArraySelector instantiate() {
		return ArraysFactory.eINSTANCE.createArraySelector();
	}
}