package cipm.consistency.fitests.similarity.java.initialiser.literals;

import org.emftext.language.java.literals.LiteralsFactory;
import org.emftext.language.java.literals.OctalIntegerLiteral;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class OctalIntegerLiteralInitialiser extends AbstractInitialiserBase implements IOctalIntegerLiteralInitialiser {
	@Override
	public IOctalIntegerLiteralInitialiser newInitialiser() {
		return new OctalIntegerLiteralInitialiser();
	}

	@Override
	public OctalIntegerLiteral instantiate() {
		return LiteralsFactory.eINSTANCE.createOctalIntegerLiteral();
	}
}