package cipm.consistency.initialisers.eobject.java.arrays;

import org.emftext.language.java.arrays.ArrayTypeable;

import cipm.consistency.initialisers.eobject.java.commons.ICommentableInitialiser;

public interface IArrayTypeableInitialiser extends ICommentableInitialiser {
	@Override
	public ArrayTypeable instantiate();

}
