package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.SwitchCase;

public interface ISwitchCaseInitialiser extends IStatementListContainerInitialiser {
	@Override
	public SwitchCase instantiate();

}
