package cipm.consistency.initialisers.eobject.java.statements;

import org.emftext.language.java.statements.JumpLabel;

import cipm.consistency.initialisers.eobject.java.commons.INamedElementInitialiser;

public interface IJumpLabelInitialiser
		extends INamedElementInitialiser, IStatementInitialiser, IStatementContainerInitialiser {
	@Override
	public JumpLabel instantiate();

}
