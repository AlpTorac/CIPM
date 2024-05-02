package cipm.consistency.fitests.similarity.java.initialiser.literals;

import org.emftext.language.java.literals.CharacterLiteral;
import org.emftext.language.java.literals.LiteralsFactory;

public class CharacterLiteralInitialiser implements ICharacterLiteralInitialiser {
	@Override
	public ICharacterLiteralInitialiser newInitialiser() {
		return new CharacterLiteralInitialiser();
	}

	@Override
	public CharacterLiteral instantiate() {
		return LiteralsFactory.eINSTANCE.createCharacterLiteral();
	}
}