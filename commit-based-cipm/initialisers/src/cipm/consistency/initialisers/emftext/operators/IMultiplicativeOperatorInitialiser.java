package cipm.consistency.initialisers.emftext.operators;

import org.emftext.language.java.operators.MultiplicativeOperator;

public interface IMultiplicativeOperatorInitialiser extends IOperatorInitialiser {
	@Override
	public MultiplicativeOperator instantiate();

}
