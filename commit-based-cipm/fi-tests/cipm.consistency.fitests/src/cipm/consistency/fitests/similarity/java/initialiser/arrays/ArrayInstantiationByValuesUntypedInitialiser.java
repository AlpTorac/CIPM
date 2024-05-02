package cipm.consistency.fitests.similarity.java.initialiser.arrays;

import org.emftext.language.java.arrays.ArrayInstantiationByValuesUntyped;
import org.emftext.language.java.arrays.ArraysFactory;

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