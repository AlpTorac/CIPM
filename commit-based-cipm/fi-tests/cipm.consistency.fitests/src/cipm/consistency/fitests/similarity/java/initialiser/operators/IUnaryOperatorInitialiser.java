package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.UnaryOperator;

public interface IUnaryOperatorInitialiser extends IOperatorInitialiser {
	@Override
	public UnaryOperator instantiate();

}