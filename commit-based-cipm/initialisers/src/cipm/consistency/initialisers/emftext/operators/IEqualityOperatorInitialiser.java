package cipm.consistency.initialisers.emftext.operators;

import org.emftext.language.java.operators.EqualityOperator;

public interface IEqualityOperatorInitialiser extends IOperatorInitialiser {
	@Override
	public EqualityOperator instantiate();

}