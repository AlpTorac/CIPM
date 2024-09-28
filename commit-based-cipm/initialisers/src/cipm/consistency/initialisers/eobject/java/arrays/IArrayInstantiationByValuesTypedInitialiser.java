package cipm.consistency.initialisers.eobject.java.arrays;

import org.emftext.language.java.arrays.ArrayInstantiationByValuesTyped;

import cipm.consistency.initialisers.eobject.java.types.ITypedElementInitialiser;

public interface IArrayInstantiationByValuesTypedInitialiser
		extends IArrayInstantiationByValuesInitialiser, ITypedElementInitialiser {
	@Override
	public ArrayInstantiationByValuesTyped instantiate();
}
