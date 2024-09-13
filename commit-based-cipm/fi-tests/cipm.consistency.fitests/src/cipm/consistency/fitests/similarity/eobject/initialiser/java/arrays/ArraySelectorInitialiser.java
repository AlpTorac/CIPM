package cipm.consistency.fitests.similarity.eobject.initialiser.java.arrays;

import org.emftext.language.java.arrays.ArraySelector;
import org.emftext.language.java.arrays.ArraysFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class ArraySelectorInitialiser extends AbstractInitialiserBase implements IArraySelectorInitialiser {
	@Override
	public IArraySelectorInitialiser newInitialiser() {
		return new ArraySelectorInitialiser();
	}

	@Override
	public ArraySelector instantiate() {
		return ArraysFactory.eINSTANCE.createArraySelector();
	}
}