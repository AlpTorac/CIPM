package cipm.consistency.fitests.similarity.java.initialiser.variables;

import org.emftext.language.java.variables.AdditionalLocalVariable;

import cipm.consistency.fitests.similarity.java.initialiser.instantiations.IInitializableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.references.IReferenceableElementInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.types.ITypedElementInitialiser;

public interface IAdditionalLocalVariableInitialiser extends IInitializableInitialiser,
	IReferenceableElementInitialiser, ITypedElementInitialiser {
	@Override
	public AdditionalLocalVariable instantiate();
}
