package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.parameters.OrdinaryParameter;
import org.emftext.language.java.parameters.Parameter;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.fitests.similarity.java.initialiser.parameters.IParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.parameters.OrdinaryParameterInitialiser;

/**
 * An interface that can be implemented by tests, which work with
 * {@link Parameter} instances. <br>
 * <br>
 * Contains methods that can be used to create {@link Parameter} instances.
 */
public interface UsesParameters extends UsesTypeReferences {
	/**
	 * A variant of
	 * {@link #createMinimalParamWithClsTarget(IParameterInitialiser, String, String)}
	 * that returns a {@link OrdinaryParameter} instance.
	 */
	public default OrdinaryParameter createMinimalOrdParamWithClsTarget(String paramName, String targetName) {
		return (OrdinaryParameter) this.createMinimalParamWithClsTarget(new OrdinaryParameterInitialiser(), paramName,
				targetName);
	}

	/**
	 * A variant of
	 * {@link #createMinimalParameter(IParameterInitialiser, String, TypeReference)}
	 * that constructs a {@link ClassifierReference} pointing at a {@link Class}
	 * with the given targetName.
	 * 
	 * @param targetName The name of the {@link Classifier} the
	 *                   {@link TypeReference} of the constructed instance will
	 *                   point at
	 * 
	 * @see {@link #createMinimalClsRef(String)}
	 */
	public default Parameter createMinimalParamWithClsTarget(IParameterInitialiser init, String paramName,
			String targetName) {
		return this.createMinimalParameter(init, paramName, this.createMinimalClsRef(targetName));
	}

	/**
	 * @param init      The initialiser that will be used to construct the instance
	 * @param paramName The name of the instance to be constructed
	 * @param tref      The type reference of the instance to be constructed
	 * @return A {@link Parameter} instance with the given parameters
	 */
	public default Parameter createMinimalParameter(IParameterInitialiser init, String paramName, TypeReference tref) {
		Parameter result = init.instantiate();
		init.setName(result, paramName);
		init.setTypeReference(result, tref);
		return result;
	}
}
