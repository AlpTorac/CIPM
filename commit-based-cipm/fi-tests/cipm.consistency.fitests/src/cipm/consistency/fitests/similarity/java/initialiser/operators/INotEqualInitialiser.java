package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.NotEqual;

public interface INotEqualInitialiser extends IEqualityOperatorInitialiser {
    @Override
    public NotEqual instantiate();

}
