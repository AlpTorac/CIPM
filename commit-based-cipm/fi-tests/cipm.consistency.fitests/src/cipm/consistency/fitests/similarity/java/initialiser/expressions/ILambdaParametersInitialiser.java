package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.LambdaParameters;

import cipm.consistency.fitests.similarity.java.initialiser.parameters.IParametrizableInitialiser;

public interface ILambdaParametersInitialiser extends IParametrizableInitialiser {
    @Override
    public LambdaParameters instantiate();

}
