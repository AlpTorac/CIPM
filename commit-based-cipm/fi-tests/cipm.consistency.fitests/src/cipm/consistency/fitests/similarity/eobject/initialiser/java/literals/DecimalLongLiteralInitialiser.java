package cipm.consistency.fitests.similarity.eobject.initialiser.java.literals;

import org.emftext.language.java.literals.DecimalLongLiteral;
import org.emftext.language.java.literals.LiteralsFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class DecimalLongLiteralInitialiser extends AbstractInitialiserBase implements IDecimalLongLiteralInitialiser {
	@Override
	public IDecimalLongLiteralInitialiser newInitialiser() {
		return new DecimalLongLiteralInitialiser();
	}

	@Override
	public DecimalLongLiteral instantiate() {
		return LiteralsFactory.eINSTANCE.createDecimalLongLiteral();
	}
}