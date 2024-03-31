package org.splevo.jamopp.diffing.similarity.switches;

import java.lang.reflect.Array;

import org.eclipse.emf.ecore.EObject;
import org.splevo.jamopp.diffing.similarity.SimilarityChecker;

/**
	* Similarity decision for array elements.
	* <p>
	* All array elements are strongly typed. They have no identifying attributes. Their location
	* and runtime type are assumed to be checked before this switch is called. So nothing to check
	* here.
*/
public class ArraysSimilaritySwitch extends AbstractSimilaritySwitch {
	
	@Override
	public Class<?> getComparisonSubjectType() {
		return Array.newInstance(Object.class, 0).getClass();
	}

	@Override
	public Boolean handle(EObject eo1, EObject eo2, SimilarityChecker sc) {
		return this.defaultCase(eo1, eo2);
	}

	@Override
	public Boolean defaultCase(EObject eo1, EObject eo2) {
		return Boolean.TRUE;
	}

}
