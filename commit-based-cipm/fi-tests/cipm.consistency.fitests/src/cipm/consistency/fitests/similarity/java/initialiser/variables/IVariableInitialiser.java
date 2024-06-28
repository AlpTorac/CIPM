package cipm.consistency.fitests.similarity.java.initialiser.variables;

import cipm.consistency.fitests.similarity.java.initialiser.generics.ITypeArgumentableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.references.IReferenceableElementInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.types.ITypedElementInitialiser;

/**
 * createMethodCall methods in {@link Variable} do not modify Variable instances.
 * 
 * @author atora
 */
public interface IVariableInitialiser extends IReferenceableElementInitialiser,
	ITypeArgumentableInitialiser, ITypedElementInitialiser {
}
