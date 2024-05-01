package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.statements.DefaultSwitchRule;
import org.emftext.language.java.statements.StatementsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.IDefaultSwitchRuleInitialiser;

public class DefaultSwitchRuleInitialiser implements IDefaultSwitchRuleInitialiser {
	@Override
	public IDefaultSwitchRuleInitialiser newInitialiser() {
		return new DefaultSwitchRuleInitialiser();
	}

	@Override
	public DefaultSwitchRule instantiate() {
		return StatementsFactory.eINSTANCE.createDefaultSwitchRule();
	}
}