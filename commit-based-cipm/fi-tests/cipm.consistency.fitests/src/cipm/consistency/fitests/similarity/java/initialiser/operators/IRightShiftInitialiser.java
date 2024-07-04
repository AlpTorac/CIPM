package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.RightShift;

public interface IRightShiftInitialiser extends IShiftOperatorInitialiser {
    @Override
    public RightShift instantiate();

}
