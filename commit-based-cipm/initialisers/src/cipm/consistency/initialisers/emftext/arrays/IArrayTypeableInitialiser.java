package cipm.consistency.initialisers.emftext.arrays;

import org.emftext.language.java.arrays.ArrayTypeable;

import cipm.consistency.initialisers.emftext.commons.ICommentableInitialiser;

public interface IArrayTypeableInitialiser extends ICommentableInitialiser {
	@Override
	public ArrayTypeable instantiate();

}
