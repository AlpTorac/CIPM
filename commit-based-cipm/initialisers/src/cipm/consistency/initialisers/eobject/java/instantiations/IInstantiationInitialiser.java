package cipm.consistency.initialisers.eobject.java.instantiations;

import org.emftext.language.java.instantiations.Instantiation;

import cipm.consistency.initialisers.eobject.java.generics.ICallTypeArgumentableInitialiser;
import cipm.consistency.initialisers.eobject.java.references.IArgumentableInitialiser;
import cipm.consistency.initialisers.eobject.java.references.IReferenceInitialiser;

public interface IInstantiationInitialiser
		extends IArgumentableInitialiser, IReferenceInitialiser, ICallTypeArgumentableInitialiser {
	@Override
	public Instantiation instantiate();
}
