package cipm.consistency.fitests.similarity.eobject.initialiser.java.literals;

import org.emftext.language.java.literals.BooleanLiteral;
import org.emftext.language.java.literals.LiteralsFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class BooleanLiteralInitialiser extends AbstractInitialiserBase implements IBooleanLiteralInitialiser {
	@Override
	public IBooleanLiteralInitialiser newInitialiser() {
		return new BooleanLiteralInitialiser();
	}

	@Override
	public BooleanLiteral instantiate() {
		return LiteralsFactory.eINSTANCE.createBooleanLiteral();
	}
}