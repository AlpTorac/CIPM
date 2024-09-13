package cipm.consistency.fitests.similarity.eobject.initialiser.java.arrays;

import org.emftext.language.java.arrays.ArrayInstantiation;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.references.IReferenceInitialiser;

public interface IArrayInstantiationInitialiser extends IReferenceInitialiser {
	@Override
	public ArrayInstantiation instantiate();
}
