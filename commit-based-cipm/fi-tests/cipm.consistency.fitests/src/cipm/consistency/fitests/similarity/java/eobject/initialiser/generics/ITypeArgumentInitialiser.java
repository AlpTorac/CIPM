package cipm.consistency.fitests.similarity.java.eobject.initialiser.generics;

import org.emftext.language.java.generics.TypeArgument;

import cipm.consistency.fitests.similarity.java.eobject.initialiser.arrays.IArrayTypeableInitialiser;

public interface ITypeArgumentInitialiser extends IArrayTypeableInitialiser {
	@Override
	public TypeArgument instantiate();

}
