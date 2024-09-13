package cipm.consistency.fitests.similarity.java.eobject.initialiser.statements;

import org.emftext.language.java.statements.SwitchRule;

public interface ISwitchRuleInitialiser extends ISwitchCaseInitialiser {
	@Override
	public SwitchRule instantiate();

}
