package cipm.consistency.initialisers.eobject.java.arrays;

import org.emftext.language.java.arrays.ArrayDimension;

import cipm.consistency.initialisers.eobject.java.annotations.IAnnotableInitialiser;
import cipm.consistency.initialisers.eobject.java.commons.ICommentableInitialiser;

public interface IArrayDimensionInitialiser extends IAnnotableInitialiser, ICommentableInitialiser {
	@Override
	public ArrayDimension instantiate();
}
