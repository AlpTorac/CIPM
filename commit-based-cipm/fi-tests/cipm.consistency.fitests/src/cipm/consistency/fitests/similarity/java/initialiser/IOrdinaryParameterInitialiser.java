package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.commons.NamespaceAwareElement;
import org.emftext.language.java.parameters.OrdinaryParameter;

public interface IOrdinaryParameterInitialiser extends IParameterInitialiser {
	@Override
	public OrdinaryParameter instantiate();
}
