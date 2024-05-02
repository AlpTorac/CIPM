package cipm.consistency.fitests.similarity.java.initialiser.literals;

import org.emftext.language.java.literals.BinaryIntegerLiteral;
import org.emftext.language.java.literals.LiteralsFactory;

public class BinaryIntegerLiteralInitialiser implements IBinaryIntegerLiteralInitialiser {
	@Override
	public IBinaryIntegerLiteralInitialiser newInitialiser() {
		return new BinaryIntegerLiteralInitialiser();
	}
	
	@Override
	public BinaryIntegerLiteral instantiate() {
		return LiteralsFactory.eINSTANCE.createBinaryIntegerLiteral();
	}
}