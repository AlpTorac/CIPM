package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.parameters.OrdinaryParameter;

public interface IOrdinaryParameterInitialiser extends IParameterInitialiser {
	@Override
	public OrdinaryParameter instantiate();
	
	@Override
	public default OrdinaryParameter minimalInstantiation() {
		return (OrdinaryParameter) IParameterInitialiser.super.minimalInstantiation();
	}
}
