package cipm.consistency.fitests.similarity.eobject.initialiser.java.statements;

import org.emftext.language.java.statements.JumpLabel;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.commons.INamedElementInitialiser;

public interface IJumpLabelInitialiser
		extends INamedElementInitialiser, IStatementInitialiser, IStatementContainerInitialiser {
	@Override
	public JumpLabel instantiate();

}
