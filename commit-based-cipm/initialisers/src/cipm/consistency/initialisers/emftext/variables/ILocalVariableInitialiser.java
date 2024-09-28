package cipm.consistency.initialisers.emftext.variables;

import org.emftext.language.java.variables.AdditionalLocalVariable;
import org.emftext.language.java.variables.LocalVariable;

import cipm.consistency.initialisers.emftext.instantiations.IInitializableInitialiser;
import cipm.consistency.initialisers.emftext.modifiers.IAnnotableAndModifiableInitialiser;

public interface ILocalVariableInitialiser
		extends IAnnotableAndModifiableInitialiser, IInitializableInitialiser, IVariableInitialiser {
	@Override
	public LocalVariable instantiate();

	public default boolean addAdditionalLocalVariable(LocalVariable lv, AdditionalLocalVariable alv) {
		if (alv != null) {
			lv.getAdditionalLocalVariables().add(alv);
			return lv.getAdditionalLocalVariables().contains(alv);
		}
		return true;
	}

	public default boolean addAdditionalLocalVariables(LocalVariable lv, AdditionalLocalVariable[] alvs) {
		return this.doMultipleModifications(lv, alvs, this::addAdditionalLocalVariable);
	}
}
