package cipm.consistency.fitests.similarity.java.initialiser.variables;

import org.emftext.language.java.variables.AdditionalLocalVariable;
import org.emftext.language.java.variables.LocalVariable;

import cipm.consistency.fitests.similarity.java.initialiser.instantiations.IInitializableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.modifiers.IAnnotableAndModifiableInitialiser;

public interface ILocalVariableInitialiser extends
	IAnnotableAndModifiableInitialiser,
	IInitializableInitialiser,
	IVariableInitialiser {
	public default void addAdditionalLocalVariable(LocalVariable lv, AdditionalLocalVariable alv) {
		if (alv != null) {
			lv.getAdditionalLocalVariables().add(alv);
			assert lv.getAdditionalLocalVariables().contains(alv);
		}
	}
	
	public default void addAdditionalLocalVariables(LocalVariable lv, AdditionalLocalVariable[] alvs) {
		this.addXs(lv, alvs, this::addAdditionalLocalVariable);
	}
}
