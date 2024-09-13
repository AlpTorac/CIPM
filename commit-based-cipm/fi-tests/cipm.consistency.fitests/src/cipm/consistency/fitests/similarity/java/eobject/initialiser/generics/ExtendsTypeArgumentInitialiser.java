package cipm.consistency.fitests.similarity.java.eobject.initialiser.generics;

import org.emftext.language.java.generics.ExtendsTypeArgument;
import org.emftext.language.java.generics.GenericsFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

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