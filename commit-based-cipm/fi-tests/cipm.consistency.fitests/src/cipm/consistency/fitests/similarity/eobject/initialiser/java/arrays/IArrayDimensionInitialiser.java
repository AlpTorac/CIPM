package cipm.consistency.fitests.similarity.eobject.initialiser.java.arrays;

import org.emftext.language.java.arrays.ArrayDimension;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.annotations.IAnnotableInitialiser;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.commons.ICommentableInitialiser;

public interface IArrayDimensionInitialiser extends IAnnotableInitialiser, ICommentableInitialiser {
	@Override
	public ArrayDimension instantiate();
}
