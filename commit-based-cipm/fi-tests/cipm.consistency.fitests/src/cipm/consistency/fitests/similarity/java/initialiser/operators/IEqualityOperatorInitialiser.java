package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.EqualityOperator;

public interface IEqualityOperatorInitialiser extends IOperatorInitialiser {
	@Override
	public EqualityOperator instantiate();

}