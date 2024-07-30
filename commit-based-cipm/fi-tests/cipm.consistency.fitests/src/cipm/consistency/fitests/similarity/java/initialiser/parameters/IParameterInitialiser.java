package cipm.consistency.fitests.similarity.java.initialiser.parameters;

import org.emftext.language.java.parameters.Parameter;

import cipm.consistency.fitests.similarity.java.initialiser.modifiers.IAnnotableAndModifiableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.variables.IVariableInitialiser;

public interface IParameterInitialiser extends IAnnotableAndModifiableInitialiser, IVariableInitialiser {
	@Override
	public Parameter instantiate();
}
