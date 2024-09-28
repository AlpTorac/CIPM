package cipm.consistency.initialisers.emftext.parameters;

import org.emftext.language.java.parameters.OrdinaryParameter;

public interface IOrdinaryParameterInitialiser extends IParameterInitialiser {
	@Override
	public OrdinaryParameter instantiate();
}
