package cipm.consistency.fitests.similarity.java.initialiser.arrays;

import org.emftext.language.java.arrays.ArrayInstantiationBySize;
import org.emftext.language.java.expressions.Expression;

import cipm.consistency.fitests.similarity.java.initialiser.types.ITypedElementInitialiser;

public interface IArrayInstantiationBySizeInitialiser extends 
	IArrayInstantiationInitialiser,
	ITypedElementInitialiser {

	@Override
	public ArrayInstantiationBySize instantiate();
	public default boolean addSize(ArrayInstantiationBySize arrIns, Expression expr) {
		if (expr != null) {
			arrIns.getSizes().add(expr);
			return arrIns.getSizes().contains(expr);
		}
		return true;
	}
	
	public default boolean addSizes(ArrayInstantiationBySize arrIns, Expression[] exprs) {
		return this.addXs(arrIns, exprs, this::addSize);
	}
}
