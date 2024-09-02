package cipm.consistency.fitests.similarity.java.initialiser.literals;

import org.emftext.language.java.literals.BinaryIntegerLiteral;
import org.emftext.language.java.literals.LiteralsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class BinaryIntegerLiteralInitialiser extends AbstractInitialiserBase
		implements IBinaryIntegerLiteralInitialiser {
	@Override
	public IBinaryIntegerLiteralInitialiser newInitialiser() {
		return new BinaryIntegerLiteralInitialiser();
	}

	@Override
	public BinaryIntegerLiteral instantiate() {
		return LiteralsFactory.eINSTANCE.createBinaryIntegerLiteral();
	}
}