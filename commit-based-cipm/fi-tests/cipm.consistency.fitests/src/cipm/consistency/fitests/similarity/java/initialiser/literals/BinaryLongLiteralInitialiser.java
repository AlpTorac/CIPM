package cipm.consistency.fitests.similarity.java.initialiser.literals;

import org.emftext.language.java.literals.BinaryLongLiteral;
import org.emftext.language.java.literals.LiteralsFactory;

public class BinaryLongLiteralInitialiser implements IBinaryLongLiteralInitialiser {
	@Override
	public IBinaryLongLiteralInitialiser newInitialiser() {
		return new BinaryLongLiteralInitialiser();
	}
	
	@Override
	public BinaryLongLiteral instantiate() {
		return LiteralsFactory.eINSTANCE.createBinaryLongLiteral();
	}
}