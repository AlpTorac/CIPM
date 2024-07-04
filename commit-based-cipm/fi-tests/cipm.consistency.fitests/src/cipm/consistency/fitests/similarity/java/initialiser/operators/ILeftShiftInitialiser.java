package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.LeftShift;

public interface ILeftShiftInitialiser extends IShiftOperatorInitialiser {
    @Override
    public LeftShift instantiate();

}
