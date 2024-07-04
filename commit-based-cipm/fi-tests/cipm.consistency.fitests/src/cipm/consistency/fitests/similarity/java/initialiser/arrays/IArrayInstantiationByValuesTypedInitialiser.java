package cipm.consistency.fitests.similarity.java.initialiser.arrays;

import org.emftext.language.java.arrays.ArrayInstantiationByValuesTyped;

import cipm.consistency.fitests.similarity.java.initialiser.types.ITypedElementInitialiser;

public interface IArrayInstantiationByValuesTypedInitialiser extends
	IArrayInstantiationByValuesInitialiser,
	ITypedElementInitialiser {
	@Override
	public ArrayInstantiationByValuesTyped instantiate();
}
