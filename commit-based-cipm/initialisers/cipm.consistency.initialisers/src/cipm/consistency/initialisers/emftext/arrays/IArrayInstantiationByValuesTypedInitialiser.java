package cipm.consistency.initialisers.emftext.arrays;

import org.emftext.language.java.arrays.ArrayInstantiationByValuesTyped;

import cipm.consistency.initialisers.emftext.types.ITypedElementInitialiser;

public interface IArrayInstantiationByValuesTypedInitialiser
		extends IArrayInstantiationByValuesInitialiser, ITypedElementInitialiser {
	@Override
	public ArrayInstantiationByValuesTyped instantiate();
}
