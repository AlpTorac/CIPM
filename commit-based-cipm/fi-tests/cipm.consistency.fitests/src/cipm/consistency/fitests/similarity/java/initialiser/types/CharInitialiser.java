package cipm.consistency.fitests.similarity.java.initialiser.types;

import org.emftext.language.java.types.Char;
import org.emftext.language.java.types.TypesFactory;

public class CharInitialiser implements ICharInitialiser {
	@Override
	public ICharInitialiser newInitialiser() {
		return new CharInitialiser();
	}
	
	@Override
	public Char instantiate() {
		return TypesFactory.eINSTANCE.createChar();
	}
}