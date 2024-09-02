package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.NormalSwitchCase;
import org.emftext.language.java.statements.StatementsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class NormalSwitchCaseInitialiser extends AbstractInitialiserBase implements INormalSwitchCaseInitialiser {
	@Override
	public INormalSwitchCaseInitialiser newInitialiser() {
		return new NormalSwitchCaseInitialiser();
	}

	@Override
	public NormalSwitchCase instantiate() {
		return StatementsFactory.eINSTANCE.createNormalSwitchCase();
	}
}