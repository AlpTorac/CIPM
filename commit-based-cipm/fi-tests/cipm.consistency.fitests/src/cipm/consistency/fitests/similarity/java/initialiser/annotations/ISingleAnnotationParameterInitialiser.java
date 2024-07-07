package cipm.consistency.fitests.similarity.java.initialiser.annotations;

import org.emftext.language.java.annotations.AnnotationValue;
import org.emftext.language.java.annotations.SingleAnnotationParameter;

import cipm.consistency.fitests.similarity.java.initialiser.ModificationMethod;

public interface ISingleAnnotationParameterInitialiser extends IAnnotationParameterInitialiser {
    @Override
    public SingleAnnotationParameter instantiate();
    
    @ModificationMethod
	public default boolean setValue(SingleAnnotationParameter sap, AnnotationValue val) {
		if (val != null) {
			sap.setValue(val);
			return sap.getValue().equals(val);
		}
		return true;
	}
}
