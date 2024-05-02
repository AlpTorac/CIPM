package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.StatementsFactory;
import org.emftext.language.java.statements.Switch;

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