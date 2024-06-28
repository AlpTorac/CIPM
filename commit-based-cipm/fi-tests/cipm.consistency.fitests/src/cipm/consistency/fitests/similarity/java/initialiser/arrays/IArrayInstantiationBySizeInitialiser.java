package cipm.consistency.fitests.similarity.java.initialiser.arrays;

import org.emftext.language.java.arrays.ArrayInstantiationBySize;
import org.emftext.language.java.expressions.Expression;

import cipm.consistency.fitests.similarity.java.initialiser.types.ITypedElementInitialiser;

public interface IArrayInstantiationBySizeInitialiser extends 
	IArrayInstantiationInitialiser,
	ITypedElementInitialiser {

	public default void addSize(ArrayInstantiationBySize arrIns, Expression expr) {
		if (expr != null) {
			arrIns.getSizes().add(expr);
			assert arrIns.getSizes().contains(expr);
		}
	}
	
	public default void addSizes(ArrayInstantiationBySize arrIns, Expression[] exprs) {
		this.addXs(arrIns, exprs, this::addSize);
	}
}
