package cipm.consistency.fitests.similarity.java.eobject.initialiser.generics;

import org.emftext.language.java.generics.QualifiedTypeArgument;

import cipm.consistency.fitests.similarity.java.eobject.initialiser.types.ITypedElementInitialiser;

public interface IQualifiedTypeArgumentInitialiser extends ITypeArgumentInitialiser, ITypedElementInitialiser {
	@Override
	public QualifiedTypeArgument instantiate();
}
