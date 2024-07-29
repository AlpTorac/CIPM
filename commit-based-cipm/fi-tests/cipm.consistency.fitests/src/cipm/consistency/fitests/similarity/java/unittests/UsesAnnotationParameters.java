package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.annotations.AnnotationValue;
import org.emftext.language.java.annotations.SingleAnnotationParameter;

import cipm.consistency.fitests.similarity.java.initialiser.annotations.SingleAnnotationParameterInitialiser;

/**
 * An interface that can be implemented by tests, which work with
 * {@link AnnotationParameter} instances. <br>
 * <br>
 * Contains methods that can be used to create {@link AnnotationParameter}
 * instances.
 */
public interface UsesAnnotationParameters extends UsesAnnotationValues {
	/**
	 * @param val The value of the instance to be constructed
	 * @return A {@link SingleAnnotationParameter} instance with the given
	 *         parameter.
	 */
	public default SingleAnnotationParameter createSingleAnnoParam(AnnotationValue val) {
		var init = new SingleAnnotationParameterInitialiser();
		SingleAnnotationParameter result = init.instantiate();
		init.setValue(result, val);
		return result;
	}

	/**
	 * A variant of {@link #createSingleAnnoParam(AnnotationValue)}, where the
	 * parameter is a {@link NullLiteral}.
	 * 
	 * @see {@link #createNullLiteral()}
	 */
	public default SingleAnnotationParameter createSingleNullAnnoParam() {
		return this.createSingleAnnoParam(this.createNullLiteral());
	}

	/**
	 * A variant of {@link #createSingleAnnoParam(AnnotationValue)}, where the
	 * parameter is a {@link StringReference} with the given value.
	 * 
	 * @see {@link #createMinimalSR(String)}
	 */
	public default SingleAnnotationParameter createSingleStrAnnoParam(String val) {
		return this.createSingleAnnoParam(this.createMinimalSR(val));
	}
}
