package cipm.consistency.initialisers.emftext.statements;

import org.emftext.language.java.statements.SwitchRule;

public interface ISwitchRuleInitialiser extends ISwitchCaseInitialiser {
	@Override
	public SwitchRule instantiate();

}