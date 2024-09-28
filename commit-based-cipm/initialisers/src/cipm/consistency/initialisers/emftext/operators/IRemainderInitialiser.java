package cipm.consistency.initialisers.emftext.operators;

import org.emftext.language.java.operators.Remainder;

public interface IRemainderInitialiser extends IMultiplicativeOperatorInitialiser {
	@Override
	public Remainder instantiate();

}
