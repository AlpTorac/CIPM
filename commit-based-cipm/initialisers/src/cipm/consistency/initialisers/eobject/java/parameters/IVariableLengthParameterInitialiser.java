package cipm.consistency.initialisers.eobject.java.parameters;

import org.emftext.language.java.parameters.VariableLengthParameter;

import cipm.consistency.initialisers.eobject.java.annotations.IAnnotableInitialiser;

public interface IVariableLengthParameterInitialiser extends IAnnotableInitialiser, IParameterInitialiser {
	@Override
	public VariableLengthParameter instantiate();
}
