package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.statements.JumpLabel;
import org.emftext.language.java.statements.StatementsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.IJumpLabelInitialiser;

public class JumpLabelInitialiser implements IJumpLabelInitialiser {
	@Override
	public IJumpLabelInitialiser newInitialiser() {
		return new JumpLabelInitialiser();
	}

	@Override
	public JumpLabel instantiate() {
		return StatementsFactory.eINSTANCE.createJumpLabel();
	}
}