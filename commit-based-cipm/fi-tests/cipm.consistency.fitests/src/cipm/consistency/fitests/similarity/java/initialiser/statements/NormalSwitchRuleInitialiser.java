package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.NormalSwitchRule;
import org.emftext.language.java.statements.StatementsFactory;

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