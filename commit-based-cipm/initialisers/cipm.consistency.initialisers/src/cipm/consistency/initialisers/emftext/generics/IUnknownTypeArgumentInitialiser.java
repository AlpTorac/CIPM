package cipm.consistency.initialisers.emftext.generics;

import org.emftext.language.java.generics.UnknownTypeArgument;

import cipm.consistency.initialisers.emftext.annotations.IAnnotableInitialiser;

public interface IUnknownTypeArgumentInitialiser extends IAnnotableInitialiser, ITypeArgumentInitialiser {
	@Override
	public UnknownTypeArgument instantiate();
}