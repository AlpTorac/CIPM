package cipm.consistency.fitests.similarity.java.initialiser.literals;

import org.emftext.language.java.literals.DecimalIntegerLiteral;
import org.emftext.language.java.literals.LiteralsFactory;

public class DecimalIntegerLiteralInitialiser implements IDecimalIntegerLiteralInitialiser {
	@Override
	public IDecimalIntegerLiteralInitialiser newInitialiser() {
		return new DecimalIntegerLiteralInitialiser();
	}
	
	@Override
	public DecimalIntegerLiteral instantiate() {
		return LiteralsFactory.eINSTANCE.createDecimalIntegerLiteral();
	}
}