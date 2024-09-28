package cipm.consistency.initialisers.emftext.operators;

import org.emftext.language.java.operators.ShiftOperator;

public interface IShiftOperatorInitialiser extends IOperatorInitialiser {
	@Override
	public ShiftOperator instantiate();

}
