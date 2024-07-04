package cipm.consistency.fitests.similarity.java.initialiser.members;

import org.emftext.language.java.members.AdditionalField;

import cipm.consistency.fitests.similarity.java.initialiser.instantiations.IInitializableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.references.IReferenceableElementInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.types.ITypedElementInitialiser;

public interface IAdditionalFieldInitialiser extends IInitializableInitialiser,
	IReferenceableElementInitialiser,
	ITypedElementInitialiser {
	@Override
	public AdditionalField instantiate();
}
