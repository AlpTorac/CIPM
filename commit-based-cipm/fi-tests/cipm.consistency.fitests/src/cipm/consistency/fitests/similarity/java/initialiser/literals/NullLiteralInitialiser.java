package cipm.consistency.fitests.similarity.java.initialiser.literals;

import org.emftext.language.java.literals.LiteralsFactory;
import org.emftext.language.java.literals.NullLiteral;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class NullLiteralInitialiser extends AbstractInitialiserBase implements INullLiteralInitialiser {
	@Override
	public INullLiteralInitialiser newInitialiser() {
		return new NullLiteralInitialiser();
	}
	
	@Override
	public NullLiteral instantiate() {
		return LiteralsFactory.eINSTANCE.createNullLiteral();
	}
}