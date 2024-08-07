package cipm.consistency.fitests.similarity.java.initialiser.types;

import org.emftext.language.java.types.InferableType;

public interface IInferableTypeInitialiser extends ITypedElementExtensionInitialiser, ITypeReferenceInitialiser {
	@Override
	public InferableType instantiate();
}
