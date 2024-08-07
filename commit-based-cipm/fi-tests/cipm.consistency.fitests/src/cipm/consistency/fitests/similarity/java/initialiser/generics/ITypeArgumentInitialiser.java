package cipm.consistency.fitests.similarity.java.initialiser.generics;

import org.emftext.language.java.generics.TypeArgument;

import cipm.consistency.fitests.similarity.java.initialiser.arrays.IArrayTypeableInitialiser;

public interface ITypeArgumentInitialiser extends IArrayTypeableInitialiser {
	@Override
	public TypeArgument instantiate();

}
