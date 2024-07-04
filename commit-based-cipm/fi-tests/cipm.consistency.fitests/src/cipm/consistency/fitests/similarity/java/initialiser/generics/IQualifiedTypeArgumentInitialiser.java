package cipm.consistency.fitests.similarity.java.initialiser.generics;

import org.emftext.language.java.generics.QualifiedTypeArgument;

import cipm.consistency.fitests.similarity.java.initialiser.types.ITypedElementInitialiser;

public interface IQualifiedTypeArgumentInitialiser extends
	ITypeArgumentInitialiser,
	ITypedElementInitialiser {
	@Override
	public QualifiedTypeArgument instantiate();
}
