package cipm.consistency.fitests.similarity.java.initialiser.literals;

import org.emftext.language.java.literals.HexDoubleLiteral;
import org.emftext.language.java.literals.LiteralsFactory;

public class HexDoubleLiteralInitialiser implements IHexDoubleLiteralInitialiser {
	@Override
	public IHexDoubleLiteralInitialiser newInitialiser() {
		return new HexDoubleLiteralInitialiser();
	}

	@Override
	public HexDoubleLiteral instantiate() {
		return LiteralsFactory.eINSTANCE.createHexDoubleLiteral();
	}
}