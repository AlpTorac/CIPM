package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.arrays.ArraysFactory;
import org.emftext.language.java.arrays.ArrayInitializer;

import cipm.consistency.fitests.similarity.java.initialiser.IArrayInitializerInitialiser;

public class ArrayInitializerInitialiser implements IArrayInitializerInitialiser {
	@Override
	public IArrayInitializerInitialiser newInitialiser() {
		return new ArrayInitializerInitialiser();
	}

	@Override
	public ArrayInitializer instantiate() {
		return ArraysFactory.eINSTANCE.createArrayInitializer();
	}
}
