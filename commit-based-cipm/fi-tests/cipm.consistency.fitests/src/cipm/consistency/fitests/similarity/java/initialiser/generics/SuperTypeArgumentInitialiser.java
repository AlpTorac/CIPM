package cipm.consistency.fitests.similarity.java.initialiser.generics;

import org.emftext.language.java.generics.GenericsFactory;
import org.emftext.language.java.generics.SuperTypeArgument;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class SuperTypeArgumentInitialiser extends AbstractInitialiserBase implements ISuperTypeArgumentInitialiser {
	@Override
	public ISuperTypeArgumentInitialiser newInitialiser() {
		return new SuperTypeArgumentInitialiser();
	}

	@Override
	public SuperTypeArgument instantiate() {
		return GenericsFactory.eINSTANCE.createSuperTypeArgument();
	}
}