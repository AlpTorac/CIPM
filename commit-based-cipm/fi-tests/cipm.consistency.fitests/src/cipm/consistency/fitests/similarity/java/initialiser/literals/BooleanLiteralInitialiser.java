package cipm.consistency.fitests.similarity.java.initialiser.literals;

import org.emftext.language.java.literals.BooleanLiteral;
import org.emftext.language.java.literals.LiteralsFactory;

public class BooleanLiteralInitialiser implements IBooleanLiteralInitialiser {
	@Override
	public IBooleanLiteralInitialiser newInitialiser() {
		return new BooleanLiteralInitialiser();
	}

	@Override
	public BooleanLiteral instantiate() {
		return LiteralsFactory.eINSTANCE.createBooleanLiteral();
	}
}