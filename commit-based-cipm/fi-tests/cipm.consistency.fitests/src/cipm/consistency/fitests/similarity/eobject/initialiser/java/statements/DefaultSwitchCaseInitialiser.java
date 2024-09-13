package cipm.consistency.fitests.similarity.eobject.initialiser.java.statements;

import org.emftext.language.java.statements.DefaultSwitchCase;
import org.emftext.language.java.statements.StatementsFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class DefaultSwitchCaseInitialiser extends AbstractInitialiserBase implements IDefaultSwitchCaseInitialiser {
	@Override
	public IDefaultSwitchCaseInitialiser newInitialiser() {
		return new DefaultSwitchCaseInitialiser();
	}

	@Override
	public DefaultSwitchCase instantiate() {
		return StatementsFactory.eINSTANCE.createDefaultSwitchCase();
	}
}