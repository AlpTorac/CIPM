package cipm.consistency.fitests.similarity.java.initialiser.arrays;

import org.emftext.language.java.arrays.ArrayInitializationValue;
import org.emftext.language.java.arrays.ArrayInitializer;

import cipm.consistency.fitests.similarity.java.initialiser.annotations.IAnnotationValueInitialiser;

public interface IArrayInitializerInitialiser extends IAnnotationValueInitialiser,
	IArrayInitializationValueInitialiser {
	public default void addInitialValue(ArrayInitializer ai, ArrayInitializationValue aiv) {
		if (aiv != null) {
			ai.getInitialValues().add(aiv);
			assert ai.getInitialValues().contains(aiv);
		}
	}
	
	public default void addInitialValues(ArrayInitializer ai, ArrayInitializationValue[] aivs) {
		this.addXs(ai, aivs, this::addInitialValue);
	}
}
