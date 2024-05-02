package cipm.consistency.fitests.similarity.java.initialiser.variables;

import org.emftext.language.java.containers.JavaRoot;
import org.emftext.language.java.variables.AdditionalLocalVariable;
import org.emftext.language.java.variables.LocalVariable;

import cipm.consistency.fitests.similarity.java.initialiser.IAnnotableAndModifiableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitializableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IVariableInitialiser;

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
}
