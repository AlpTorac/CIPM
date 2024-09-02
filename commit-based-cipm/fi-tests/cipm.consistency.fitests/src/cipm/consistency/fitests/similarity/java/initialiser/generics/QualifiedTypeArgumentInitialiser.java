package cipm.consistency.fitests.similarity.java.initialiser.generics;

import org.emftext.language.java.generics.GenericsFactory;
import org.emftext.language.java.generics.QualifiedTypeArgument;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class QualifiedTypeArgumentInitialiser extends AbstractInitialiserBase
		implements IQualifiedTypeArgumentInitialiser {
	@Override
	public IQualifiedTypeArgumentInitialiser newInitialiser() {
		return new QualifiedTypeArgumentInitialiser();
	}

	@Override
	public QualifiedTypeArgument instantiate() {
		return GenericsFactory.eINSTANCE.createQualifiedTypeArgument();
	}
}