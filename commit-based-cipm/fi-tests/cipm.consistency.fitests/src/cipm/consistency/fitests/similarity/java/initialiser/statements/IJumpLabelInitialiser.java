package cipm.consistency.fitests.similarity.java.initialiser.statements;

import cipm.consistency.fitests.similarity.java.initialiser.commons.INamedElementInitialiser;

import org.emftext.language.java.statements.JumpLabel;

public interface IJumpLabelInitialiser
		extends INamedElementInitialiser, IStatementInitialiser, IStatementContainerInitialiser {
	@Override
	public JumpLabel instantiate();

}
