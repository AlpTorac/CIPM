package cipm.consistency.fitests.similarity.java.eobject.initialiser.arrays;

import org.emftext.language.java.arrays.ArraysFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

import org.emftext.language.java.arrays.ArrayInitializer;

public class ArrayInitializerInitialiser extends AbstractInitialiserBase implements IArrayInitializerInitialiser {
	@Override
	public IArrayInitializerInitialiser newInitialiser() {
		return new ArrayInitializerInitialiser();
	}

	@Override
	public ArrayInitializer instantiate() {
		return ArraysFactory.eINSTANCE.createArrayInitializer();
	}
}
