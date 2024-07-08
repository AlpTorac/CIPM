package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.StatementsFactory;
import org.emftext.language.java.statements.Assert;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class AssertInitialiser extends AbstractInitialiserBase implements IAssertInitialiser {
	@Override
	public IAssertInitialiser newInitialiser() {
		return new AssertInitialiser();
	}

	@Override
	public Assert instantiate() {
		return StatementsFactory.eINSTANCE.createAssert();
	}
}