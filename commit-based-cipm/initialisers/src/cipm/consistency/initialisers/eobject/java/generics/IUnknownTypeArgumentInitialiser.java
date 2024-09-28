package cipm.consistency.initialisers.eobject.java.generics;

import org.emftext.language.java.generics.UnknownTypeArgument;

import cipm.consistency.initialisers.eobject.java.annotations.IAnnotableInitialiser;

public interface IUnknownTypeArgumentInitialiser extends IAnnotableInitialiser, ITypeArgumentInitialiser {
	@Override
	public UnknownTypeArgument instantiate();
}
