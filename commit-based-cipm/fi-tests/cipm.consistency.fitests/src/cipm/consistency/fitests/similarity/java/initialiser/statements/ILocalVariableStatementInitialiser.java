package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.LocalVariableStatement;
import org.emftext.language.java.variables.LocalVariable;

import cipm.consistency.fitests.similarity.java.initialiser.ModificationMethod;

import org.emftext.language.java.statements.LocalVariableStatement;

import org.emftext.language.java.statements.LocalVariableStatement;

public interface ILocalVariableStatementInitialiser extends IStatementInitialiser {
    @Override
    public LocalVariableStatement instantiate();
    @ModificationMethod
	public default boolean setVariable(LocalVariableStatement lvs, LocalVariable lv) {
		if (lv != null) {
			lvs.setVariable(lv);
			return lvs.getVariable().equals(lv);
		}
		return true;
	}
}
