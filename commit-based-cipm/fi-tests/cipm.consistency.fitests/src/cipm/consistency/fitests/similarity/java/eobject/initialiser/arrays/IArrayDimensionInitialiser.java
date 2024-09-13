package cipm.consistency.fitests.similarity.java.eobject.initialiser.arrays;

import org.emftext.language.java.arrays.ArrayDimension;

import cipm.consistency.fitests.similarity.java.eobject.initialiser.annotations.IAnnotableInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.commons.ICommentableInitialiser;

public interface IArrayDimensionInitialiser extends IAnnotableInitialiser, ICommentableInitialiser {
	@Override
	public ArrayDimension instantiate();
}
