package cipm.consistency.fitests.similarity.java.initialiser.generics;

import org.emftext.language.java.generics.GenericsFactory;
import org.emftext.language.java.generics.UnknownTypeArgument;

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