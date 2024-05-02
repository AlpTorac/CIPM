package cipm.consistency.fitests.similarity.java.initialiser.literals;

import org.emftext.language.java.literals.DecimalLongLiteral;
import org.emftext.language.java.literals.LiteralsFactory;

public class DecimalLongLiteralInitialiser implements IDecimalLongLiteralInitialiser {
	@Override
	public IDecimalLongLiteralInitialiser newInitialiser() {
		return new DecimalLongLiteralInitialiser();
	}
	
	@Override
	public DecimalLongLiteral instantiate() {
		return LiteralsFactory.eINSTANCE.createDecimalLongLiteral();
	}
}