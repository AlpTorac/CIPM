package cipm.consistency.initialisers.emftext.operators;

import org.emftext.language.java.operators.LeftShift;

public interface ILeftShiftInitialiser extends IShiftOperatorInitialiser {
	@Override
	public LeftShift instantiate();

}
