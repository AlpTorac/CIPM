package cipm.consistency.fitests.similarity.java.initialiser.generics;

import org.emftext.language.java.generics.GenericsFactory;
import org.emftext.language.java.generics.SuperTypeArgument;

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