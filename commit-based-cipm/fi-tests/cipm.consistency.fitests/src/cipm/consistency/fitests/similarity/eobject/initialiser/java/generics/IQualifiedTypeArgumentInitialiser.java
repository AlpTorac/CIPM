package cipm.consistency.fitests.similarity.eobject.initialiser.java.generics;

import org.emftext.language.java.generics.QualifiedTypeArgument;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.types.ITypedElementInitialiser;

public interface IQualifiedTypeArgumentInitialiser extends ITypeArgumentInitialiser, ITypedElementInitialiser {
	@Override
	public QualifiedTypeArgument instantiate();
}
