package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.Multiplication;

public interface IMultiplicationInitialiser extends IMultiplicativeOperatorInitialiser {
    @Override
    public Multiplication instantiate();

}
