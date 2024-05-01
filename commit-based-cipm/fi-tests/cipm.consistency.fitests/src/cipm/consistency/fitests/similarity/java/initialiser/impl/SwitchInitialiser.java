package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.statements.StatementsFactory;
import org.emftext.language.java.statements.Switch;

import cipm.consistency.fitests.similarity.java.initialiser.ISwitchInitialiser;

public class SwitchInitialiser implements ISwitchInitialiser {
	@Override
	public ISwitchInitialiser newInitialiser() {
		return new SwitchInitialiser();
	}

	@Override
	public Switch instantiate() {
		return StatementsFactory.eINSTANCE.createSwitch();
	}
}