package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.InstanceOfExpressionChild;

public interface IInstanceOfExpressionChildInitialiser extends IEqualityExpressionChildInitialiser {
	@Override
	public InstanceOfExpressionChild instantiate();

}