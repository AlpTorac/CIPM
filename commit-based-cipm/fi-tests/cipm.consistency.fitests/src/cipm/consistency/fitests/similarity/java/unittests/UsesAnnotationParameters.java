package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.annotations.AnnotationValue;
import org.emftext.language.java.annotations.SingleAnnotationParameter;

import cipm.consistency.fitests.similarity.java.initialiser.annotations.SingleAnnotationParameterInitialiser;

public interface UsesAnnotationParameters extends UsesAnnotationValues {
	public default SingleAnnotationParameter createSingleAnnoParam(AnnotationValue val) {
		var init = new SingleAnnotationParameterInitialiser();
		SingleAnnotationParameter result = init.instantiate();
		init.initialise(result);
		init.setValue(result, val);
		return result;
	}
	
	public default SingleAnnotationParameter createSingleNullAnnoParam() {
		return this.createSingleAnnoParam(this.createNullVal());
	}
	
	public default SingleAnnotationParameter createSingleStrAnnoParam(String val) {
		return this.createSingleAnnoParam(this.createStringRefVal(val));
	}
}
