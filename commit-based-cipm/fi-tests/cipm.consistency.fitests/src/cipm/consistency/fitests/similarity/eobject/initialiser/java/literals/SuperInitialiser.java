package cipm.consistency.fitests.similarity.eobject.initialiser.java.literals;

import org.emftext.language.java.literals.LiteralsFactory;
import org.emftext.language.java.literals.Super;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class SuperInitialiser extends AbstractInitialiserBase implements ISuperInitialiser {
	@Override
	public ISuperInitialiser newInitialiser() {
		return new SuperInitialiser();
	}

	@Override
	public Super instantiate() {
		return LiteralsFactory.eINSTANCE.createSuper();
	}
}