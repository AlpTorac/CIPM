package cipm.consistency.fitests.similarity.eobject.initialiser.java.arrays;

import org.emftext.language.java.arrays.ArrayInstantiationByValuesTyped;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.types.ITypedElementInitialiser;

public interface IArrayInstantiationByValuesTypedInitialiser
		extends IArrayInstantiationByValuesInitialiser, ITypedElementInitialiser {
	@Override
	public ArrayInstantiationByValuesTyped instantiate();
}
