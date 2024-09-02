package cipm.consistency.fitests.similarity.java.initialiser.types;

import org.emftext.language.java.types.ClassifierReference;
import org.emftext.language.java.types.TypesFactory;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class ClassifierReferenceInitialiser extends AbstractInitialiserBase implements IClassifierReferenceInitialiser {
	@Override
	public IClassifierReferenceInitialiser newInitialiser() {
		return new ClassifierReferenceInitialiser();
	}

	@Override
	public ClassifierReference instantiate() {
		return TypesFactory.eINSTANCE.createClassifierReference();
	}
}