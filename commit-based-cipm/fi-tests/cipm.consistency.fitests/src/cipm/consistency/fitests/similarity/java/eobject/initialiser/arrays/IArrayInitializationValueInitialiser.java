package cipm.consistency.fitests.similarity.java.eobject.initialiser.arrays;

import org.emftext.language.java.arrays.ArrayInitializationValue;

import cipm.consistency.fitests.similarity.java.eobject.initialiser.commons.ICommentableInitialiser;

public interface IArrayInitializationValueInitialiser extends ICommentableInitialiser {
	@Override
	public ArrayInitializationValue instantiate();

}
