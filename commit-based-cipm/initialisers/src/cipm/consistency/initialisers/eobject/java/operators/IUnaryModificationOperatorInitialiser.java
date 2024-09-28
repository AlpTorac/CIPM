package cipm.consistency.initialisers.eobject.java.operators;

import org.emftext.language.java.operators.UnaryModificationOperator;

public interface IUnaryModificationOperatorInitialiser extends IOperatorInitialiser {
	@Override
	public UnaryModificationOperator instantiate();

}