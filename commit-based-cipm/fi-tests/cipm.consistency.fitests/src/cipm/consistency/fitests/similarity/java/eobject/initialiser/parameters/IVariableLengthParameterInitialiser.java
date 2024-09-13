package cipm.consistency.fitests.similarity.java.eobject.initialiser.parameters;

import org.emftext.language.java.parameters.VariableLengthParameter;

import cipm.consistency.fitests.similarity.java.eobject.initialiser.annotations.IAnnotableInitialiser;

public interface IVariableLengthParameterInitialiser extends IAnnotableInitialiser, IParameterInitialiser {
	@Override
	public VariableLengthParameter instantiate();
}
