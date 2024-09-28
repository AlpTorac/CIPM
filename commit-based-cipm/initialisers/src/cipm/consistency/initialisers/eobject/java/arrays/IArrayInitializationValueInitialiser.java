package cipm.consistency.initialisers.eobject.java.arrays;

import org.emftext.language.java.arrays.ArrayInitializationValue;

import cipm.consistency.initialisers.eobject.java.commons.ICommentableInitialiser;

public interface IArrayInitializationValueInitialiser extends ICommentableInitialiser {
	@Override
	public ArrayInitializationValue instantiate();

}
