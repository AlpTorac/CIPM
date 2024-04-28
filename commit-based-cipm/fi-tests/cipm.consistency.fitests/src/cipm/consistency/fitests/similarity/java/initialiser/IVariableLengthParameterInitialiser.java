package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.parameters.VariableLengthParameter;
import org.emftext.language.java.variables.Variable;

public interface IVariableLengthParameterInitialiser extends IAnnotableInitialiser, IParameterInitialiser {
	@Override
	public VariableLengthParameter instantiate();
}
