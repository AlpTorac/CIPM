package cipm.consistency.fitests.similarity.java.initialiser.arrays;

import org.emftext.language.java.arrays.ArrayDimension;

import cipm.consistency.fitests.similarity.java.initialiser.annotations.IAnnotableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.commons.ICommentableInitialiser;

public interface IArrayDimensionInitialiser extends IAnnotableInitialiser, ICommentableInitialiser {
	@Override
	public ArrayDimension instantiate();
}
