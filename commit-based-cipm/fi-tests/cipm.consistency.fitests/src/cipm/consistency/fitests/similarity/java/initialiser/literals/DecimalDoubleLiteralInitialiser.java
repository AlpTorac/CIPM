package cipm.consistency.fitests.similarity.java.initialiser.literals;

import org.emftext.language.java.literals.DecimalDoubleLiteral;
import org.emftext.language.java.literals.LiteralsFactory;

public class DecimalDoubleLiteralInitialiser implements IDecimalDoubleLiteralInitialiser {
	@Override
	public IDecimalDoubleLiteralInitialiser newInitialiser() {
		return new DecimalDoubleLiteralInitialiser();
	}

	@Override
	public DecimalDoubleLiteral instantiate() {
		return LiteralsFactory.eINSTANCE.createDecimalDoubleLiteral();
	}
}