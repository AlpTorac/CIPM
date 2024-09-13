package cipm.consistency.fitests.similarity.java.eobject.initialiser.statements;

import org.emftext.language.java.statements.JumpLabel;

import cipm.consistency.fitests.similarity.java.eobject.initialiser.commons.INamedElementInitialiser;

public interface IJumpLabelInitialiser
		extends INamedElementInitialiser, IStatementInitialiser, IStatementContainerInitialiser {
	@Override
	public JumpLabel instantiate();

}
