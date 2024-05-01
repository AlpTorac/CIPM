package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.statements.LocalVariableStatement;
import org.emftext.language.java.statements.StatementsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.ILocalVariableStatementInitialiser;

public class LocalVariableStatementInitialiser implements ILocalVariableStatementInitialiser {
	@Override
	public ILocalVariableStatementInitialiser newInitialiser() {
		return new LocalVariableStatementInitialiser();
	}

	@Override
	public LocalVariableStatement instantiate() {
		return StatementsFactory.eINSTANCE.createLocalVariableStatement();
	}
}