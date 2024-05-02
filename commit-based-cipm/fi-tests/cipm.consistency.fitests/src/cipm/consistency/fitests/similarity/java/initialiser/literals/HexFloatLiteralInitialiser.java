package cipm.consistency.fitests.similarity.java.initialiser.literals;

import org.emftext.language.java.literals.HexFloatLiteral;
import org.emftext.language.java.literals.LiteralsFactory;

public class HexFloatLiteralInitialiser implements IHexFloatLiteralInitialiser {
	@Override
	public IHexFloatLiteralInitialiser newInitialiser() {
		return new HexFloatLiteralInitialiser();
	}
	
	@Override
	public HexFloatLiteral instantiate() {
		return LiteralsFactory.eINSTANCE.createHexFloatLiteral();
	}
}