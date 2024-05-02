package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.DefaultSwitchCase;
import org.emftext.language.java.statements.StatementsFactory;

public class DefaultSwitchCaseInitialiser implements IDefaultSwitchCaseInitialiser {
	@Override
	public IDefaultSwitchCaseInitialiser newInitialiser() {
		return new DefaultSwitchCaseInitialiser();
	}

	@Override
	public DefaultSwitchCase instantiate() {
		return StatementsFactory.eINSTANCE.createDefaultSwitchCase();
	}
}