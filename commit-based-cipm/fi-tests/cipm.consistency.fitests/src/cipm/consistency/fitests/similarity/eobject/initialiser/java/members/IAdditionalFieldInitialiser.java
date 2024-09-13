package cipm.consistency.fitests.similarity.eobject.initialiser.java.members;

import org.emftext.language.java.members.AdditionalField;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.instantiations.IInitializableInitialiser;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.references.IReferenceableElementInitialiser;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.types.ITypedElementInitialiser;

public interface IAdditionalFieldInitialiser
		extends IInitializableInitialiser, IReferenceableElementInitialiser, ITypedElementInitialiser {
	@Override
	public AdditionalField instantiate();
}
