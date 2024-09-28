package cipm.consistency.initialisers.emftext.generics;

import org.emftext.language.java.generics.ExtendsTypeArgument;
import org.emftext.language.java.generics.GenericsFactory;

import cipm.consistency.initialisers.AbstractInitialiserBase;

public class ExtendsTypeArgumentInitialiser extends AbstractInitialiserBase implements IExtendsTypeArgumentInitialiser {
	@Override
	public IExtendsTypeArgumentInitialiser newInitialiser() {
		return new ExtendsTypeArgumentInitialiser();
	}

	@Override
	public ExtendsTypeArgument instantiate() {
		return GenericsFactory.eINSTANCE.createExtendsTypeArgument();
	}
}