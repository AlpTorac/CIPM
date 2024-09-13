package cipm.consistency.fitests.similarity.java.eobject.initialiser.literals;

import org.emftext.language.java.literals.CharacterLiteral;
import org.emftext.language.java.literals.LiteralsFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class CharacterLiteralInitialiser extends AbstractInitialiserBase implements ICharacterLiteralInitialiser {
	@Override
	public ICharacterLiteralInitialiser newInitialiser() {
		return new CharacterLiteralInitialiser();
	}

	@Override
	public CharacterLiteral instantiate() {
		return LiteralsFactory.eINSTANCE.createCharacterLiteral();
	}
}