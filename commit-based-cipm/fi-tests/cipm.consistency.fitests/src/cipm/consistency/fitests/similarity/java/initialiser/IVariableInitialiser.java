package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.generics.TypeParametrizable;
import org.emftext.language.java.variables.Variable;

import cipm.consistency.fitests.similarity.java.initialiser.testable.ITypeArgumentableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.ITypedElementInitialiser;

public interface IVariableInitialiser extends IReferenceableElementInitialiser,
	ITypeArgumentableInitialiser, ITypedElementInitialiser {
	// TODO: Inspect createMethodCall methods
}
