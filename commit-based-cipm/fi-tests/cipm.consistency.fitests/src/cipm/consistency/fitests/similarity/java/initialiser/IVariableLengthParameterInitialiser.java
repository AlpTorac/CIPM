package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.parameters.VariableLengthParameter;

public interface IVariableLengthParameterInitialiser extends IAnnotableInitialiser, IParameterInitialiser {
	@Override
	public VariableLengthParameter instantiate();
	
	@Override
	public default VariableLengthParameter minimalInstantiation() {
		return (VariableLengthParameter) IParameterInitialiser.super.minimalInstantiation();
	}
}
