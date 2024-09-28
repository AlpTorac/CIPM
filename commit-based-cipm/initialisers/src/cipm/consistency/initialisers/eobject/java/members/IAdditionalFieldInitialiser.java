package cipm.consistency.initialisers.eobject.java.members;

import org.emftext.language.java.members.AdditionalField;

import cipm.consistency.initialisers.eobject.java.instantiations.IInitializableInitialiser;
import cipm.consistency.initialisers.eobject.java.references.IReferenceableElementInitialiser;
import cipm.consistency.initialisers.eobject.java.types.ITypedElementInitialiser;

public interface IAdditionalFieldInitialiser
		extends IInitializableInitialiser, IReferenceableElementInitialiser, ITypedElementInitialiser {
	@Override
	public AdditionalField instantiate();
}
