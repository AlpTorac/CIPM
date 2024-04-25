package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.variables.AdditionalLocalVariable;
import org.emftext.language.java.variables.LocalVariable;

public interface ILocalVariableInitialiser extends IVariableInitialiser {
	@Override
	public LocalVariable instantiate();
	
	@Override
	public default LocalVariable minimalInstantiation() {
		return (LocalVariable) IVariableInitialiser.super.minimalInstantiation();
	}
	
	public default void addAdditionalLocalVariable(LocalVariable lv, AdditionalLocalVariable alv) {
		if (alv != null) {
			lv.getAdditionalLocalVariables().add(alv);
			assert lv.getAdditionalLocalVariables().contains(alv);
		}
	}
}
