package cipm.consistency.fitests.similarity.java.eobject.initialiser.statements;

import org.emftext.language.java.statements.DefaultSwitchRule;
import org.emftext.language.java.statements.StatementsFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class DefaultSwitchRuleInitialiser extends AbstractInitialiserBase implements IDefaultSwitchRuleInitialiser {
	@Override
	public IDefaultSwitchRuleInitialiser newInitialiser() {
		return new DefaultSwitchRuleInitialiser();
	}

	@Override
	public DefaultSwitchRule instantiate() {
		return StatementsFactory.eINSTANCE.createDefaultSwitchRule();
	}
}