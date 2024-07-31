package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.LocalVariableStatement;
import org.emftext.language.java.variables.LocalVariable;

public interface ILocalVariableStatementInitialiser extends IStatementInitialiser {
	@Override
	public LocalVariableStatement instantiate();

	public default boolean setVariable(LocalVariableStatement lvs, LocalVariable var) {
		if (var != null) {
			lvs.setVariable(var);
			return lvs.getVariable().equals(var);
		}
		return true;
	}
}
