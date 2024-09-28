package cipm.consistency.initialisers.emftext.members;

import org.emftext.language.java.members.AdditionalField;

import cipm.consistency.initialisers.emftext.instantiations.IInitializableInitialiser;
import cipm.consistency.initialisers.emftext.references.IReferenceableElementInitialiser;
import cipm.consistency.initialisers.emftext.types.ITypedElementInitialiser;

public interface IAdditionalFieldInitialiser
		extends IInitializableInitialiser, IReferenceableElementInitialiser, ITypedElementInitialiser {
	@Override
	public AdditionalField instantiate();
}
