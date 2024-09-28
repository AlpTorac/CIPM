package cipm.consistency.initialisers.emftext.arrays;

import org.emftext.language.java.arrays.ArrayDimension;

import cipm.consistency.initialisers.emftext.annotations.IAnnotableInitialiser;
import cipm.consistency.initialisers.emftext.commons.ICommentableInitialiser;

public interface IArrayDimensionInitialiser extends IAnnotableInitialiser, ICommentableInitialiser {
	@Override
	public ArrayDimension instantiate();
}
