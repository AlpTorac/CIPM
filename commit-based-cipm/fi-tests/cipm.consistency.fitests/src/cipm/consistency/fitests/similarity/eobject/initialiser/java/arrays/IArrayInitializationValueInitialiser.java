package cipm.consistency.fitests.similarity.eobject.initialiser.java.arrays;

import org.emftext.language.java.arrays.ArrayInitializationValue;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.commons.ICommentableInitialiser;

public interface IArrayInitializationValueInitialiser extends ICommentableInitialiser {
	@Override
	public ArrayInitializationValue instantiate();

}
