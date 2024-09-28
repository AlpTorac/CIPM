package cipm.consistency.initialisers.emftext.statements;

import org.emftext.language.java.statements.JumpLabel;

import cipm.consistency.initialisers.emftext.commons.INamedElementInitialiser;

public interface IJumpLabelInitialiser
		extends INamedElementInitialiser, IStatementInitialiser, IStatementContainerInitialiser {
	@Override
	public JumpLabel instantiate();

}
