package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.Addition;

public interface IAdditionInitialiser extends IAdditiveOperatorInitialiser {
    @Override
    public Addition instantiate();

}
