package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.types.InferableType;
import org.emftext.language.java.types.TypesFactory;

import cipm.consistency.fitests.similarity.java.initialiser.IInferableTypeInitialiser;

public class InferableTypeInitialiser implements IInferableTypeInitialiser {
	@Override
	public IInferableTypeInitialiser newInitialiser() {
		return new InferableTypeInitialiser();
	}

	@Override
	public InferableType instantiate() {
		return TypesFactory.eINSTANCE.createInferableType();
	}
}