package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.EmptyStatement;

public interface IEmptyStatementInitialiser extends IStatementInitialiser {
	@Override
	public EmptyStatement instantiate();

}
