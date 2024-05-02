package cipm.consistency.fitests.similarity.java.initialiser.literals;

import org.emftext.language.java.literals.LiteralsFactory;
import org.emftext.language.java.literals.This;

public class ThisInitialiser implements IThisInitialiser {
	@Override
	public IThisInitialiser newInitialiser() {
		return new ThisInitialiser();
	}

	@Override
	public This instantiate() {
		return LiteralsFactory.eINSTANCE.createThis();
	}
}