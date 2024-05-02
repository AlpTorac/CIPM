package cipm.consistency.fitests.similarity.java.initialiser.literals;

import org.emftext.language.java.literals.HexIntegerLiteral;
import org.emftext.language.java.literals.LiteralsFactory;

public class HexIntegerLiteralInitialiser implements IHexIntegerLiteralInitialiser {
	@Override
	public IHexIntegerLiteralInitialiser newInitialiser() {
		return new HexIntegerLiteralInitialiser();
	}
	
	@Override
	public HexIntegerLiteral instantiate() {
		return LiteralsFactory.eINSTANCE.createHexIntegerLiteral();
	}
}