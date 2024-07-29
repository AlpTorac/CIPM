package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.arrays.ArrayInitializationValue;
import org.emftext.language.java.arrays.ArrayInitializer;
import org.emftext.language.java.expressions.Expression;

import cipm.consistency.fitests.similarity.java.initialiser.arrays.ArrayInitializerInitialiser;

/**
 * An interface that can be implemented by tests, which work with
 * {@link ArrayInitializer} instances. <br>
 * <br>
 * Contains methods that can be used to create {@link ArrayInitializer}
 * instances.
 */
public interface UsesArrayInitializers {
	/**
	 * @param aivs The values of the instance to be constructed
	 * @return An {@link ArrayInitializer} with the given parameter
	 */
	public default ArrayInitializer createMinimalArrayInitializer(ArrayInitializationValue[] aivs) {
		var init = new ArrayInitializerInitialiser();
		ArrayInitializer result = init.instantiate();
		init.addInitialValues(result, aivs);
		return result;
	}

	/**
	 * A variant of
	 * {@link #createMinimalArrayInitializer(ArrayInitializationValue[])}, where the
	 * parameter is an array containing of only the given expr as an
	 * {@link ArrayInitializationValue}.
	 */
	public default ArrayInitializer createMinimalArrayInitializer(Expression expr) {
		return this.createMinimalArrayInitializer(new ArrayInitializationValue[] { expr });
	}
}
