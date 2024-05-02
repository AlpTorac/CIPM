package cipm.consistency.fitests.similarity.java.initialiser.literals;

import org.emftext.language.java.literals.LiteralsFactory;
import org.emftext.language.java.literals.NullLiteral;

public class NullLiteralInitialiser implements INullLiteralInitialiser {
	@Override
	public INullLiteralInitialiser newInitialiser() {
		return new NullLiteralInitialiser();
	}
	
	@Override
	public NullLiteral instantiate() {
		return LiteralsFactory.eINSTANCE.createNullLiteral();
	}
}