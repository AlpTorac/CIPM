package cipm.consistency.fitests.similarity.java.eobject.initialiser.statements;

import org.emftext.language.java.statements.StatementsFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

import org.emftext.language.java.statements.Assert;

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