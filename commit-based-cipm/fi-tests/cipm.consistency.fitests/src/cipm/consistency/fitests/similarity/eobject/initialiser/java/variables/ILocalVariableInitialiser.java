package cipm.consistency.fitests.similarity.eobject.initialiser.java.variables;

import org.emftext.language.java.variables.AdditionalLocalVariable;
import org.emftext.language.java.variables.LocalVariable;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.instantiations.IInitializableInitialiser;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.modifiers.IAnnotableAndModifiableInitialiser;

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
