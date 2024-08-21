package cipm.consistency.fitests.similarity.java.initialiser.instantiations;

import org.emftext.language.java.instantiations.InstantiationsFactory;
import org.emftext.language.java.instantiations.NewConstructorCallWithInferredTypeArguments;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class NewConstructorCallWithInferredTypeArgumentsInitialiser extends AbstractInitialiserBase
		implements INewConstructorCallWithInferredTypeArgumentsInitialiser {
	@Override
	public INewConstructorCallWithInferredTypeArgumentsInitialiser newInitialiser() {
		return new NewConstructorCallWithInferredTypeArgumentsInitialiser();
	}

	@Override
	public NewConstructorCallWithInferredTypeArguments instantiate() {
		return InstantiationsFactory.eINSTANCE.createNewConstructorCallWithInferredTypeArguments();
	}
}