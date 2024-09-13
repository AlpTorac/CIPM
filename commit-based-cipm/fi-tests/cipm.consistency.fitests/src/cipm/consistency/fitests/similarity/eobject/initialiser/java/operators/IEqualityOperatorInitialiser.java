package cipm.consistency.fitests.similarity.eobject.initialiser.java.operators;

import org.emftext.language.java.operators.EqualityOperator;

public interface IEqualityOperatorInitialiser extends IOperatorInitialiser {
	@Override
	public EqualityOperator instantiate();

}