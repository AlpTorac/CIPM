package cipm.consistency.initialisers.eobject.java.operators;

import org.emftext.language.java.operators.UnaryOperator;

public interface IUnaryOperatorInitialiser extends IOperatorInitialiser {
	@Override
	public UnaryOperator instantiate();

}