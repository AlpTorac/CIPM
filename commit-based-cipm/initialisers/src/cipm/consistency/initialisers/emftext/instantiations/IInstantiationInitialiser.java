package cipm.consistency.initialisers.emftext.instantiations;

import org.emftext.language.java.instantiations.Instantiation;

import cipm.consistency.initialisers.emftext.generics.ICallTypeArgumentableInitialiser;
import cipm.consistency.initialisers.emftext.references.IArgumentableInitialiser;
import cipm.consistency.initialisers.emftext.references.IReferenceInitialiser;

public interface IInstantiationInitialiser
		extends IArgumentableInitialiser, IReferenceInitialiser, ICallTypeArgumentableInitialiser {
	@Override
	public Instantiation instantiate();
}
