package cipm.consistency.initialisers.emftext.parameters;

import org.emftext.language.java.parameters.VariableLengthParameter;

import cipm.consistency.initialisers.emftext.annotations.IAnnotableInitialiser;

public interface IVariableLengthParameterInitialiser extends IAnnotableInitialiser, IParameterInitialiser {
	@Override
	public VariableLengthParameter instantiate();
}
