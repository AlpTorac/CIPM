package cipm.consistency.initialisers.emftext.parameters;

import org.emftext.language.java.parameters.Parameter;

import cipm.consistency.initialisers.emftext.modifiers.IAnnotableAndModifiableInitialiser;
import cipm.consistency.initialisers.emftext.variables.IVariableInitialiser;

public interface IParameterInitialiser extends IAnnotableAndModifiableInitialiser, IVariableInitialiser {
	@Override
	public Parameter instantiate();
}
