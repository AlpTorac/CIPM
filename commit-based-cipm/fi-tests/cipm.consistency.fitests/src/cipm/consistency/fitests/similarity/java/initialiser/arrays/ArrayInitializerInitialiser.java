package cipm.consistency.fitests.similarity.java.initialiser.arrays;

import org.emftext.language.java.arrays.ArraysFactory;
import org.emftext.language.java.arrays.ArrayInitializer;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

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
