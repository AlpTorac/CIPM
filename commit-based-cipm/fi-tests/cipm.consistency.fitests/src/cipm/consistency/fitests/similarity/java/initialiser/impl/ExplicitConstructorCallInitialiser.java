package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.instantiations.ExplicitConstructorCall;
import org.emftext.language.java.instantiations.InstantiationsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.IExplicitConstructorCallInitialiser;

public class ExplicitConstructorCallInitialiser implements IExplicitConstructorCallInitialiser {
	@Override
	public IExplicitConstructorCallInitialiser newInitialiser() {
		return new ExplicitConstructorCallInitialiser();
	}

	@Override
	public ExplicitConstructorCall instantiate() {
		return InstantiationsFactory.eINSTANCE.createExplicitConstructorCall();
	}
}