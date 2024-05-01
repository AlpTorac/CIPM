package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.generics.GenericsFactory;
import org.emftext.language.java.generics.UnknownTypeArgument;

import cipm.consistency.fitests.similarity.java.initialiser.IUnknownTypeArgumentInitialiser;

public class UnknownTypeArgumentInitialiser implements IUnknownTypeArgumentInitialiser {
	@Override
	public IUnknownTypeArgumentInitialiser newInitialiser() {
		return new UnknownTypeArgumentInitialiser();
	}

	@Override
	public UnknownTypeArgument instantiate() {
		return GenericsFactory.eINSTANCE.createUnknownTypeArgument();
	}
}