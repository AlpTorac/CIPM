package cipm.consistency.fitests.similarity.eobject.initialiser.java.statements;

import org.emftext.language.java.statements.EmptyStatement;
import org.emftext.language.java.statements.StatementsFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class EmptyStatementInitialiser extends AbstractInitialiserBase implements IEmptyStatementInitialiser {
	@Override
	public IEmptyStatementInitialiser newInitialiser() {
		return new EmptyStatementInitialiser();
	}

	@Override
	public EmptyStatement instantiate() {
		return StatementsFactory.eINSTANCE.createEmptyStatement();
	}
}