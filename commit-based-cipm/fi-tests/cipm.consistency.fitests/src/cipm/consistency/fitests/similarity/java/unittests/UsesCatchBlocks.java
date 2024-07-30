package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.parameters.OrdinaryParameter;
import org.emftext.language.java.statements.CatchBlock;

import cipm.consistency.fitests.similarity.java.initialiser.statements.CatchBlockInitialiser;

/**
 * An interface that can be implemented by tests, which work with
 * {@link CatchBlock} instances. <br>
 * <br>
 * Contains methods that can be used to create {@link CatchBlock} instances.
 */
public interface UsesCatchBlocks extends UsesParameters {
	/**
	 * @param op The parameter of the instance to be constructed
	 * @return A {@link CatchBlock} instance with the given parameter
	 */
	public default CatchBlock createMinimalCB(OrdinaryParameter op) {
		var cbInit = new CatchBlockInitialiser();
		var cb = cbInit.instantiate();
		cbInit.setParameter(cb, op);
		return cb;
	}

	/**
	 * A variant of {@link #createMinimalCB(OrdinaryParameter)}, where an
	 * {@link OrdinaryParameter} instance is constructed and used.
	 * 
	 * @param paramName  The name of the {@link OrdinaryParameter} instance that
	 *                   will be constructed
	 * @param targetName The name of the entity that the type reference of the
	 *                   {@link OrdinaryParameter} will point at
	 * 
	 * @see {@link #createMinimalOrdParamWithClsTarget(String, String)}
	 */
	public default CatchBlock createMinimalCB(String paramName, String targetName) {
		return this.createMinimalCB(this.createMinimalOrdParamWithClsTarget(paramName, targetName));
	}
}
