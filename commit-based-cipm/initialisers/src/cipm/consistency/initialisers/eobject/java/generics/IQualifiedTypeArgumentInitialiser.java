package cipm.consistency.initialisers.eobject.java.generics;

import org.emftext.language.java.generics.QualifiedTypeArgument;

import cipm.consistency.initialisers.eobject.java.types.ITypedElementInitialiser;

public interface IQualifiedTypeArgumentInitialiser extends ITypeArgumentInitialiser, ITypedElementInitialiser {
	@Override
	public QualifiedTypeArgument instantiate();
}
