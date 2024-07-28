package cipm.consistency.fitests.similarity.java.initialiser.arrays;

import org.emftext.language.java.arrays.ArrayInitializationValue;
import org.emftext.language.java.arrays.ArrayInitializer;

import cipm.consistency.fitests.similarity.java.initialiser.annotations.IAnnotationValueInitialiser;

public interface IArrayInitializerInitialiser extends IAnnotationValueInitialiser,
	IArrayInitializationValueInitialiser {
	@Override
	public ArrayInitializer instantiate();
	public default boolean addInitialValue(ArrayInitializer ai, ArrayInitializationValue aiv) {
		if (aiv != null) {
			ai.getInitialValues().add(aiv);
			return ai.getInitialValues().contains(aiv);
		}
		return true;
	}
	
	public default boolean addInitialValues(ArrayInitializer ai, ArrayInitializationValue[] aivs) {
		return this.doMultipleModifications(ai, aivs, this::addInitialValue);
	}
}
