package cipm.consistency.fitests.similarity.java.initialiser.arrays;

import org.emftext.language.java.arrays.ArraySelector;
import org.emftext.language.java.arrays.ArraysFactory;

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