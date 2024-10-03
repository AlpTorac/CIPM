package cipm.consistency.initialisers.jamopp.arrays;

import org.emftext.language.java.arrays.ArrayTypeable;

import cipm.consistency.initialisers.jamopp.commons.ICommentableInitialiser;

public interface IArrayTypeableInitialiser extends ICommentableInitialiser {
	@Override
	public ArrayTypeable instantiate();

}
