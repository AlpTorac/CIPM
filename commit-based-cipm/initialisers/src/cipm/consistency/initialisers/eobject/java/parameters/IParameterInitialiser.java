package cipm.consistency.initialisers.eobject.java.parameters;

import org.emftext.language.java.parameters.Parameter;

import cipm.consistency.initialisers.eobject.java.modifiers.IAnnotableAndModifiableInitialiser;
import cipm.consistency.initialisers.eobject.java.variables.IVariableInitialiser;

public interface IParameterInitialiser extends IAnnotableAndModifiableInitialiser, IVariableInitialiser {
	@Override
	public Parameter instantiate();
}
