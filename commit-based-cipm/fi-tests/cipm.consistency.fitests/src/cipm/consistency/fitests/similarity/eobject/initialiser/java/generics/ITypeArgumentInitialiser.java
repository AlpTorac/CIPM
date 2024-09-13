package cipm.consistency.fitests.similarity.eobject.initialiser.java.generics;

import org.emftext.language.java.generics.TypeArgument;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.arrays.IArrayTypeableInitialiser;

public interface ITypeArgumentInitialiser extends IArrayTypeableInitialiser {
	@Override
	public TypeArgument instantiate();

}
