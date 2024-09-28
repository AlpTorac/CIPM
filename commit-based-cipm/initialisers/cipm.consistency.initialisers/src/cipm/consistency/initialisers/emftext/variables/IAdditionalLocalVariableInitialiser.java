package cipm.consistency.initialisers.emftext.variables;

import org.emftext.language.java.variables.AdditionalLocalVariable;

import cipm.consistency.initialisers.emftext.instantiations.IInitializableInitialiser;
import cipm.consistency.initialisers.emftext.references.IReferenceableElementInitialiser;
import cipm.consistency.initialisers.emftext.types.ITypedElementInitialiser;

public interface IAdditionalLocalVariableInitialiser
		extends IInitializableInitialiser, IReferenceableElementInitialiser, ITypedElementInitialiser {
	@Override
	public AdditionalLocalVariable instantiate();
}
