package cipm.consistency.fitests.similarity.eobject.initialiser.java.literals;

import org.emftext.language.java.literals.LiteralsFactory;
import org.emftext.language.java.literals.OctalIntegerLiteral;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

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