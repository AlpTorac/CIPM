package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.StatementsFactory;
import org.emftext.language.java.statements.YieldStatement;

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