package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.statements.NormalSwitchRule;
import org.emftext.language.java.statements.StatementsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.INormalSwitchRuleInitialiser;

public class NormalSwitchRuleInitialiser implements INormalSwitchRuleInitialiser {
	@Override
	public INormalSwitchRuleInitialiser newInitialiser() {
		return new NormalSwitchRuleInitialiser();
	}

	@Override
	public NormalSwitchRule instantiate() {
		return StatementsFactory.eINSTANCE.createNormalSwitchRule();
	}
}