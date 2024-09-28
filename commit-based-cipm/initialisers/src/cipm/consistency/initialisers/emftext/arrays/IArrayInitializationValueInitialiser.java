package cipm.consistency.initialisers.emftext.arrays;

import org.emftext.language.java.arrays.ArrayInitializationValue;

import cipm.consistency.initialisers.emftext.commons.ICommentableInitialiser;

public interface IArrayInitializationValueInitialiser extends ICommentableInitialiser {
	@Override
	public ArrayInitializationValue instantiate();

}
