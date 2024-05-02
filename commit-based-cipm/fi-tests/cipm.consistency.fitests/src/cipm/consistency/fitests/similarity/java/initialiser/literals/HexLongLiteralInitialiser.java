package cipm.consistency.fitests.similarity.java.initialiser.literals;

import org.emftext.language.java.literals.HexLongLiteral;
import org.emftext.language.java.literals.LiteralsFactory;

public class HexLongLiteralInitialiser implements IHexLongLiteralInitialiser {
	@Override
	public IHexLongLiteralInitialiser newInitialiser() {
		return new HexLongLiteralInitialiser();
	}
	
	@Override
	public HexLongLiteral instantiate() {
		return LiteralsFactory.eINSTANCE.createHexLongLiteral();
	}
}