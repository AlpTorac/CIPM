package cipm.consistency.fitests.similarity.eobject.initialiser.java.statements;

import org.emftext.language.java.statements.JumpLabel;
import org.emftext.language.java.statements.StatementsFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class JumpLabelInitialiser extends AbstractInitialiserBase implements IJumpLabelInitialiser {
	@Override
	public IJumpLabelInitialiser newInitialiser() {
		return new JumpLabelInitialiser();
	}

	@Override
	public JumpLabel instantiate() {
		return StatementsFactory.eINSTANCE.createJumpLabel();
	}
}