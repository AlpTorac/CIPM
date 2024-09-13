package cipm.consistency.fitests.similarity.eobject.initialiser.java.variables;

import org.emftext.language.java.variables.AdditionalLocalVariable;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.instantiations.IInitializableInitialiser;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.references.IReferenceableElementInitialiser;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.types.ITypedElementInitialiser;

public interface IAdditionalLocalVariableInitialiser
		extends IInitializableInitialiser, IReferenceableElementInitialiser, ITypedElementInitialiser {
	@Override
	public AdditionalLocalVariable instantiate();
}
