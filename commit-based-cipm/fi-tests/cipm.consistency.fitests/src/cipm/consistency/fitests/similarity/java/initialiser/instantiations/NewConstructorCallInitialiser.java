package cipm.consistency.fitests.similarity.java.initialiser.instantiations;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.instantiations.InstantiationsFactory;
import org.emftext.language.java.instantiations.NewConstructorCall;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.fitests.similarity.java.initialiser.classifiers.ClassInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.types.ClassifierReferenceInitialiser;

public class NewConstructorCallInitialiser implements INewConstructorCallInitialiser {
	@Override
	public INewConstructorCallInitialiser newInitialiser() {
		return new NewConstructorCallInitialiser();
	}

	@Override
	public NewConstructorCall instantiate() {
		return InstantiationsFactory.eINSTANCE.createNewConstructorCall();
	}
}