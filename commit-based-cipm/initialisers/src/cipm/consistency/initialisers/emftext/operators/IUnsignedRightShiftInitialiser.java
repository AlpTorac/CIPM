package cipm.consistency.initialisers.emftext.operators;

import org.emftext.language.java.operators.UnsignedRightShift;

public interface IUnsignedRightShiftInitialiser extends IShiftOperatorInitialiser {
	@Override
	public UnsignedRightShift instantiate();

}
