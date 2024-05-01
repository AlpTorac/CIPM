package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.statements.NormalSwitchCase;
import org.emftext.language.java.statements.StatementsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.INormalSwitchCaseInitialiser;

public class NormalSwitchCaseInitialiser implements INormalSwitchCaseInitialiser {
	@Override
	public INormalSwitchCaseInitialiser newInitialiser() {
		return new NormalSwitchCaseInitialiser();
	}

	@Override
	public NormalSwitchCase instantiate() {
		return StatementsFactory.eINSTANCE.createNormalSwitchCase();
	}
}