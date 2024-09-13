package cipm.consistency.fitests.similarity.eobject.initialiser.java.statements;

import org.emftext.language.java.statements.SwitchRule;

public interface ISwitchRuleInitialiser extends ISwitchCaseInitialiser {
	@Override
	public SwitchRule instantiate();

}
