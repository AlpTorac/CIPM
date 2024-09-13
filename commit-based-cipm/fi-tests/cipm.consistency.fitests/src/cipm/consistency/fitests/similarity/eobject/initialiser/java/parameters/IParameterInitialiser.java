package cipm.consistency.fitests.similarity.eobject.initialiser.java.parameters;

import org.emftext.language.java.parameters.Parameter;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.modifiers.IAnnotableAndModifiableInitialiser;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.variables.IVariableInitialiser;

public interface IParameterInitialiser extends IAnnotableAndModifiableInitialiser, IVariableInitialiser {
	@Override
	public Parameter instantiate();
}
