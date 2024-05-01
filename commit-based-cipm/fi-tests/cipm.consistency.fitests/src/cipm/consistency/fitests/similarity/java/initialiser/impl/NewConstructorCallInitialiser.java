package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.instantiations.InstantiationsFactory;
import org.emftext.language.java.instantiations.NewConstructorCall;

import cipm.consistency.fitests.similarity.java.initialiser.INewConstructorCallInitialiser;

public class NewConstructorCallInitialiser implements INewConstructorCallInitialiser {
	@Override
	public INewConstructorCallInitialiser newInitialiser() {
		return new NewConstructorCallInitialiser();
	}

	@Override
	public NewConstructorCall instantiate() {
		return InstantiationsFactory.eINSTANCE.createNewConstructorCall();
	}
}