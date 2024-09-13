package cipm.consistency.fitests.similarity.eobject.initialiser.java.parameters;

import org.emftext.language.java.parameters.OrdinaryParameter;

public interface IOrdinaryParameterInitialiser extends IParameterInitialiser {
	@Override
	public OrdinaryParameter instantiate();
}
