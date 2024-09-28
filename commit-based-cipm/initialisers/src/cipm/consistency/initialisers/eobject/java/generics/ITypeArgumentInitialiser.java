package cipm.consistency.initialisers.eobject.java.generics;

import org.emftext.language.java.generics.TypeArgument;

import cipm.consistency.initialisers.eobject.java.arrays.IArrayTypeableInitialiser;

public interface ITypeArgumentInitialiser extends IArrayTypeableInitialiser {
	@Override
	public TypeArgument instantiate();

}
