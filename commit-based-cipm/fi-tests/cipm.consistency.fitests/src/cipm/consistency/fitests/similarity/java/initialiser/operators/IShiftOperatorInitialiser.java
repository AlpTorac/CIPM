package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.ShiftOperator;

public interface IShiftOperatorInitialiser extends IOperatorInitialiser {
    @Override
    public ShiftOperator instantiate();

}
