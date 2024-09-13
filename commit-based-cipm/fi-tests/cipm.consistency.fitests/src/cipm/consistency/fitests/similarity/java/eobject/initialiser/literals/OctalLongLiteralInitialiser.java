package cipm.consistency.fitests.similarity.java.eobject.initialiser.literals;

import org.emftext.language.java.literals.LiteralsFactory;
import org.emftext.language.java.literals.OctalLongLiteral;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class OctalLongLiteralInitialiser extends AbstractInitialiserBase implements IOctalLongLiteralInitialiser {
	@Override
	public IOctalLongLiteralInitialiser newInitialiser() {
		return new OctalLongLiteralInitialiser();
	}

	@Override
	public OctalLongLiteral instantiate() {
		return LiteralsFactory.eINSTANCE.createOctalLongLiteral();
	}
}