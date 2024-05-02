package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.EmptyStatement;
import org.emftext.language.java.statements.StatementsFactory;

public class EmptyStatementInitialiser implements IEmptyStatementInitialiser {
	@Override
	public IEmptyStatementInitialiser newInitialiser() {
		return new EmptyStatementInitialiser();
	}

	@Override
	public EmptyStatement instantiate() {
		return StatementsFactory.eINSTANCE.createEmptyStatement();
	}
}