package cipm.consistency.initialisers.emftext.generics;

import org.emftext.language.java.generics.TypeArgument;

import cipm.consistency.initialisers.emftext.arrays.IArrayTypeableInitialiser;

public interface ITypeArgumentInitialiser extends IArrayTypeableInitialiser {
	@Override
	public TypeArgument instantiate();

}
