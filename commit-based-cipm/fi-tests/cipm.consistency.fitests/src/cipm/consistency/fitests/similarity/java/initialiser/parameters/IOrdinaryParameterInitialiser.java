package cipm.consistency.fitests.similarity.java.initialiser.parameters;

import org.emftext.language.java.parameters.OrdinaryParameter;

public interface IOrdinaryParameterInitialiser extends IParameterInitialiser {
    @Override
    public OrdinaryParameter instantiate();
}
