package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.arrays.ArrayInitializationValue;
import org.emftext.language.java.arrays.ArrayInitializer;

public interface IArrayInitializerInitialiser extends IAnnotationValueInitialiser,
	IArrayInitializationValueInitialiser {
	public default void addInitialValue(ArrayInitializer ai, ArrayInitializationValue aiv) {
		if (aiv != null) {
			ai.getInitialValues().add(aiv);
			assert ai.getInitialValues().contains(aiv);
		}
	}
}
