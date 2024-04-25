package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.variables.AdditionalLocalVariable;

public interface IAdditionalLocalVariableInitialiser extends IInitializableInitialiser,
	IReferenceableElementInitialiser, ITypedElementInitialiser {
	@Override
	public AdditionalLocalVariable instantiate();
	
	@Override
	public default AdditionalLocalVariable minimalInstantiation() {
		return (AdditionalLocalVariable) IReferenceableElementInitialiser.super.minimalInstantiation();
	}
}
