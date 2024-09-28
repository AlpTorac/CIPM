package cipm.consistency.initialisers.eobject.java.arrays;

import org.emftext.language.java.arrays.ArrayInstantiation;

import cipm.consistency.initialisers.eobject.java.references.IReferenceInitialiser;

public interface IArrayInstantiationInitialiser extends IReferenceInitialiser {
	@Override
	public ArrayInstantiation instantiate();
}
