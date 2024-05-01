package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.statements.StatementsFactory;
import org.emftext.language.java.statements.YieldStatement;

import cipm.consistency.fitests.similarity.java.initialiser.IYieldStatementInitialiser;

public class YieldStatementInitialiser implements IYieldStatementInitialiser {
	@Override
	public IYieldStatementInitialiser newInitialiser() {
		return new YieldStatementInitialiser();
	}

	@Override
	public YieldStatement instantiate() {
		return StatementsFactory.eINSTANCE.createYieldStatement();
	}
}