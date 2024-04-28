package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.generics.TypeParametrizable;
import org.emftext.language.java.variables.Variable;

public interface IVariableInitialiser extends IReferenceableElementInitialiser,
	ITypeArgumentableInitialiser, ITypedElementInitialiser {
	@Override
	public Variable instantiate();
}
