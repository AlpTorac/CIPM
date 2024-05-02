package cipm.consistency.fitests.similarity.java.initialiser.annotations;

import org.emftext.language.java.annotations.AnnotationValue;
import org.emftext.language.java.annotations.SingleAnnotationParameter;

import cipm.consistency.fitests.similarity.java.initialiser.IAnnotationParameterInitialiser;

public interface ISingleAnnotationParameterInitialiser extends IAnnotationParameterInitialiser {
	public default void setValue(SingleAnnotationParameter sap, AnnotationValue val) {
		if (val != null) {
			sap.setValue(val);
			assert sap.getValue().equals(val);
		}
	}
}
