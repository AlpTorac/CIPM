package cipm.consistency.fitests.similarity.java.initialiser.generics;

import org.emftext.language.java.generics.GenericsFactory;
import org.emftext.language.java.generics.QualifiedTypeArgument;

public class QualifiedTypeArgumentInitialiser implements IQualifiedTypeArgumentInitialiser {
	@Override
	public IQualifiedTypeArgumentInitialiser newInitialiser() {
		return new QualifiedTypeArgumentInitialiser();
	}

	@Override
	public QualifiedTypeArgument instantiate() {
		return GenericsFactory.eINSTANCE.createQualifiedTypeArgument();
	}
}