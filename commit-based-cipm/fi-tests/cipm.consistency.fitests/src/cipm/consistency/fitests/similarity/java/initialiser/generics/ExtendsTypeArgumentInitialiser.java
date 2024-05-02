package cipm.consistency.fitests.similarity.java.initialiser.generics;

import org.emftext.language.java.generics.ExtendsTypeArgument;
import org.emftext.language.java.generics.GenericsFactory;

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