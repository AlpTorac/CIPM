package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.arrays.ArrayInstantiationBySize;
import org.emftext.language.java.expressions.Expression;

public interface IArrayInstantiationBySizeInitialiser extends 
	IArrayInstantiationInitialiser,
	ITypedElementInitialiser {

	public default void addSize(ArrayInstantiationBySize arrIns, Expression expr) {
		if (expr != null) {
			arrIns.getSizes().add(expr);
			assert arrIns.getSizes().contains(expr);
		}
	}
}
