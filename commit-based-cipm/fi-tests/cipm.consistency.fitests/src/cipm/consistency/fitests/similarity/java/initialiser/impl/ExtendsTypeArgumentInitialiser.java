package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.generics.ExtendsTypeArgument;
import org.emftext.language.java.generics.GenericsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.IExtendsTypeArgumentInitialiser;

public class ExtendsTypeArgumentInitialiser implements IExtendsTypeArgumentInitialiser {
	@Override
	public IExtendsTypeArgumentInitialiser newInitialiser() {
		return new ExtendsTypeArgumentInitialiser();
	}

	@Override
	public ExtendsTypeArgument instantiate() {
		return GenericsFactory.eINSTANCE.createExtendsTypeArgument();
	}
}