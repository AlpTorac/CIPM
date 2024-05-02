package cipm.consistency.fitests.similarity.java.initialiser.instantiations;

import org.emftext.language.java.instantiations.InstantiationsFactory;
import org.emftext.language.java.instantiations.NewConstructorCallWithInferredTypeArguments;

public class NewConstructorCallWithInferredTypeArgumentsInitialiser implements INewConstructorCallWithInferredTypeArgumentsInitialiser {
	@Override
	public INewConstructorCallWithInferredTypeArgumentsInitialiser newInitialiser() {
		return new NewConstructorCallWithInferredTypeArgumentsInitialiser();
	}

	@Override
	public NewConstructorCallWithInferredTypeArguments instantiate() {
		return InstantiationsFactory.eINSTANCE.createNewConstructorCallWithInferredTypeArguments();
	}
}