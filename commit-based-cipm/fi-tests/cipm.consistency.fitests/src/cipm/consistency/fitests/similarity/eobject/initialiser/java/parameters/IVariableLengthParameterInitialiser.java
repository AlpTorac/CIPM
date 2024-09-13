package cipm.consistency.fitests.similarity.eobject.initialiser.java.parameters;

import org.emftext.language.java.parameters.VariableLengthParameter;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.annotations.IAnnotableInitialiser;

public interface IVariableLengthParameterInitialiser extends IAnnotableInitialiser, IParameterInitialiser {
	@Override
	public VariableLengthParameter instantiate();
}
