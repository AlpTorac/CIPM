package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.containers.JavaRoot;
import org.emftext.language.java.variables.AdditionalLocalVariable;
import org.emftext.language.java.variables.LocalVariable;

public interface ILocalVariableInitialiser extends IVariableInitialiser {
	@Override
	public LocalVariable instantiate();
	
	public default void addAdditionalLocalVariable(LocalVariable lv, AdditionalLocalVariable alv) {
		if (alv != null) {
			lv.getAdditionalLocalVariables().add(alv);
			assert lv.getAdditionalLocalVariables().contains(alv);
		}
	}
}
