package cipm.consistency.initialisers.eobject.java.variables;

import org.emftext.language.java.variables.AdditionalLocalVariable;

import cipm.consistency.initialisers.eobject.java.instantiations.IInitializableInitialiser;
import cipm.consistency.initialisers.eobject.java.references.IReferenceableElementInitialiser;
import cipm.consistency.initialisers.eobject.java.types.ITypedElementInitialiser;

public interface IAdditionalLocalVariableInitialiser
		extends IInitializableInitialiser, IReferenceableElementInitialiser, ITypedElementInitialiser {
	@Override
	public AdditionalLocalVariable instantiate();
}
