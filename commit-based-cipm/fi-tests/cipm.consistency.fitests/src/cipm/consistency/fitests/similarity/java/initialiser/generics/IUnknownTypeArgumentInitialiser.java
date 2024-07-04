package cipm.consistency.fitests.similarity.java.initialiser.generics;

import org.emftext.language.java.generics.UnknownTypeArgument;

import cipm.consistency.fitests.similarity.java.initialiser.annotations.IAnnotableInitialiser;

public interface IUnknownTypeArgumentInitialiser extends IAnnotableInitialiser,
	ITypeArgumentInitialiser {
	@Override
	public UnknownTypeArgument instantiate();
}
