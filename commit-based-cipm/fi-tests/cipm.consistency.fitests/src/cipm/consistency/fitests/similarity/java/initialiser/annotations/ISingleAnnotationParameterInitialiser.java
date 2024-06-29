package cipm.consistency.fitests.similarity.java.initialiser.annotations;

import org.emftext.language.java.annotations.AnnotationValue;
import org.emftext.language.java.annotations.SingleAnnotationParameter;

public interface ISingleAnnotationParameterInitialiser extends IAnnotationParameterInitialiser {
	public default boolean setValue(SingleAnnotationParameter sap, AnnotationValue val) {
		if (val != null) {
			sap.setValue(val);
			return sap.getValue().equals(val);
		}
		return false;
	}
}
