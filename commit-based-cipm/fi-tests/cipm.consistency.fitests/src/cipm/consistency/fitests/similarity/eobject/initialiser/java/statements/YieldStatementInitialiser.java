package cipm.consistency.fitests.similarity.eobject.initialiser.java.statements;

import org.emftext.language.java.statements.StatementsFactory;
import org.emftext.language.java.statements.YieldStatement;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class YieldStatementInitialiser extends AbstractInitialiserBase implements IYieldStatementInitialiser {
	@Override
	public IYieldStatementInitialiser newInitialiser() {
		return new YieldStatementInitialiser();
	}

	@Override
	public YieldStatement instantiate() {
		return StatementsFactory.eINSTANCE.createYieldStatement();
	}
}