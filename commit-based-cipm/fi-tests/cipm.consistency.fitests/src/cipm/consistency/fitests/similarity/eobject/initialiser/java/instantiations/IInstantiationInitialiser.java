package cipm.consistency.fitests.similarity.eobject.initialiser.java.instantiations;

import org.emftext.language.java.instantiations.Instantiation;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.generics.ICallTypeArgumentableInitialiser;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.references.IArgumentableInitialiser;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.references.IReferenceInitialiser;

public interface IInstantiationInitialiser
		extends IArgumentableInitialiser, IReferenceInitialiser, ICallTypeArgumentableInitialiser {
	@Override
	public Instantiation instantiate();
}
