package cipm.consistency.fitests.similarity.java.initialiser;

import cipm.consistency.fitests.similarity.java.initialiser.testable.ITypeArgumentableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.ITypedElementInitialiser;

/**
 * createMethodCall methods in {@link Variable} do not modify Variable instances.
 * 
 * @author atora
 */
public interface IVariableInitialiser extends IReferenceableElementInitialiser,
	ITypeArgumentableInitialiser, ITypedElementInitialiser {
}
