package cipm.consistency.fitests.similarity.java.initialiser.instantiations;

import org.emftext.language.java.instantiations.Instantiation;

import cipm.consistency.fitests.similarity.java.initialiser.generics.ICallTypeArgumentableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.references.IArgumentableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.references.IReferenceInitialiser;

public interface IInstantiationInitialiser extends IArgumentableInitialiser,
	IReferenceInitialiser,
	ICallTypeArgumentableInitialiser {
	@Override
	public Instantiation instantiate();
}
