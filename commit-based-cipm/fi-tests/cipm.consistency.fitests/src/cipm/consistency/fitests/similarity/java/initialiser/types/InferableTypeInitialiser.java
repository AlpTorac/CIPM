package cipm.consistency.fitests.similarity.java.initialiser.types;

import org.emftext.language.java.types.InferableType;
import org.emftext.language.java.types.TypesFactory;

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