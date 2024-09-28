package cipm.consistency.initialisers.emftext.arrays;

import org.emftext.language.java.arrays.ArrayInstantiation;

import cipm.consistency.initialisers.emftext.references.IReferenceInitialiser;

public interface IArrayInstantiationInitialiser extends IReferenceInitialiser {
	@Override
	public ArrayInstantiation instantiate();
}
