package cipm.consistency.initialisers.eobject.java.statements;

import org.emftext.language.java.statements.EmptyStatement;

public interface IEmptyStatementInitialiser extends IStatementInitialiser {
	@Override
	public EmptyStatement instantiate();

}
