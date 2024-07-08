package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.NormalSwitchRule;
import org.emftext.language.java.statements.StatementsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class NormalSwitchRuleInitialiser extends AbstractInitialiserBase implements INormalSwitchRuleInitialiser {
	@Override
	public INormalSwitchRuleInitialiser newInitialiser() {
		return new NormalSwitchRuleInitialiser();
	}

	@Override
	public NormalSwitchRule instantiate() {
		return StatementsFactory.eINSTANCE.createNormalSwitchRule();
	}
}