package cipm.consistency.fitests.similarity.java.initialiser.variables;

import org.emftext.language.java.variables.AdditionalLocalVariable;
import org.emftext.language.java.variables.LocalVariable;

import cipm.consistency.fitests.similarity.java.initialiser.ModificationMethod;
import cipm.consistency.fitests.similarity.java.initialiser.instantiations.IInitializableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.modifiers.IAnnotableAndModifiableInitialiser;

public interface ILocalVariableInitialiser extends
	IAnnotableAndModifiableInitialiser,
	IInitializableInitialiser,
	IVariableInitialiser {
	@Override
	public LocalVariable instantiate();
	@ModificationMethod
	public default boolean addAdditionalLocalVariable(LocalVariable lv, AdditionalLocalVariable alv) {
		if (alv != null) {
			lv.getAdditionalLocalVariables().add(alv);
			return lv.getAdditionalLocalVariables().contains(alv);
		}
		return true;
	}
	
	public default boolean addAdditionalLocalVariables(LocalVariable lv, AdditionalLocalVariable[] alvs) {
		return this.addXs(lv, alvs, this::addAdditionalLocalVariable);
	}
}
