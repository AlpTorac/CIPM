package cipm.consistency.fitests.similarity.eobject.initialiser.java.types;

import org.emftext.language.java.types.Char;
import org.emftext.language.java.types.TypesFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class CharInitialiser extends AbstractInitialiserBase implements ICharInitialiser {
	@Override
	public ICharInitialiser newInitialiser() {
		return new CharInitialiser();
	}

	@Override
	public Char instantiate() {
		return TypesFactory.eINSTANCE.createChar();
	}
}