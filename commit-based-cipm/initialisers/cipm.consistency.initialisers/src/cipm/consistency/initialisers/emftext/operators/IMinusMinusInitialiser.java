package cipm.consistency.initialisers.emftext.operators;

import org.emftext.language.java.operators.MinusMinus;

public interface IMinusMinusInitialiser extends IUnaryModificationOperatorInitialiser {
	@Override
	public MinusMinus instantiate();

}
