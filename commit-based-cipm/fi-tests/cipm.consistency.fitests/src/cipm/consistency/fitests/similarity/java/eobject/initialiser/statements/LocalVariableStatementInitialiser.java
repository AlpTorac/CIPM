package cipm.consistency.fitests.similarity.java.eobject.initialiser.statements;

import org.emftext.language.java.statements.LocalVariableStatement;
import org.emftext.language.java.statements.StatementsFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class LocalVariableStatementInitialiser extends AbstractInitialiserBase
		implements ILocalVariableStatementInitialiser {
	@Override
	public ILocalVariableStatementInitialiser newInitialiser() {
		return new LocalVariableStatementInitialiser();
	}

	@Override
	public LocalVariableStatement instantiate() {
		return StatementsFactory.eINSTANCE.createLocalVariableStatement();
	}
}