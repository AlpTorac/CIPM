package cipm.consistency.initialisers.emftext.statements;

import org.emftext.language.java.statements.DefaultSwitchCase;

public interface IDefaultSwitchCaseInitialiser extends ISwitchCaseInitialiser {
	@Override
	public DefaultSwitchCase instantiate();

}