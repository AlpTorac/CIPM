package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.generics.GenericsFactory;
import org.emftext.language.java.generics.SuperTypeArgument;

import cipm.consistency.fitests.similarity.java.initialiser.ISuperTypeArgumentInitialiser;

public class SuperTypeArgumentInitialiser implements ISuperTypeArgumentInitialiser {
	@Override
	public ISuperTypeArgumentInitialiser newInitialiser() {
		return new SuperTypeArgumentInitialiser();
	}

	@Override
	public SuperTypeArgument instantiate() {
		return GenericsFactory.eINSTANCE.createSuperTypeArgument();
	}
}