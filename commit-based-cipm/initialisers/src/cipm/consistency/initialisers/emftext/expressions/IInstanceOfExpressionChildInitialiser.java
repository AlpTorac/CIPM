package cipm.consistency.initialisers.emftext.expressions;

import org.emftext.language.java.expressions.InstanceOfExpressionChild;

public interface IInstanceOfExpressionChildInitialiser extends IEqualityExpressionChildInitialiser {
	@Override
	public InstanceOfExpressionChild instantiate();

}