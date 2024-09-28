package cipm.consistency.initialisers.emftext.generics;

import org.emftext.language.java.generics.QualifiedTypeArgument;

import cipm.consistency.initialisers.emftext.types.ITypedElementInitialiser;

public interface IQualifiedTypeArgumentInitialiser extends ITypeArgumentInitialiser, ITypedElementInitialiser {
	@Override
	public QualifiedTypeArgument instantiate();
}
