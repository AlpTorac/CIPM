package cipm.consistency.fitests.similarity.java.initialiser.types;

import org.emftext.language.java.types.ClassifierReference;

import cipm.consistency.fitests.similarity.java.initialiser.annotations.IAnnotableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.generics.ITypeArgumentableInitialiser;

public interface IClassifierReferenceInitialiser extends ITypeArgumentableInitialiser,
	IAnnotableInitialiser,
	ITypeReferenceInitialiser {
	@Override
	public ClassifierReference instantiate();
}
