package cipm.consistency.fitests.similarity.java.eobject.initialiser.arrays;

import org.emftext.language.java.arrays.ArrayInstantiation;

import cipm.consistency.fitests.similarity.java.eobject.initialiser.references.IReferenceInitialiser;

public interface IArrayInstantiationInitialiser extends IReferenceInitialiser {
	@Override
	public ArrayInstantiation instantiate();
}
