package cipm.consistency.fitests.similarity.java.initialiser.literals;

import org.emftext.language.java.literals.LiteralsFactory;
import org.emftext.language.java.literals.OctalLongLiteral;

public class OctalLongLiteralInitialiser implements IOctalLongLiteralInitialiser {
	@Override
	public IOctalLongLiteralInitialiser newInitialiser() {
		return new OctalLongLiteralInitialiser();
	}
	
	@Override
	public OctalLongLiteral instantiate() {
		return LiteralsFactory.eINSTANCE.createOctalLongLiteral();
	}
}