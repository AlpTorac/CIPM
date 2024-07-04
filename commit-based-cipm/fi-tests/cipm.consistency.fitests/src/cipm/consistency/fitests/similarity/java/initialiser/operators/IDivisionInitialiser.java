package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.Division;

public interface IDivisionInitialiser extends IMultiplicativeOperatorInitialiser {
    @Override
    public Division instantiate();

}
