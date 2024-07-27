package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.InstanceOfExpression;
import org.emftext.language.java.expressions.InstanceOfExpressionChild;

import cipm.consistency.fitests.similarity.java.initialiser.types.ITypedElementInitialiser;

public interface IInstanceOfExpressionInitialiser extends
	IEqualityExpressionChildInitialiser,
	ITypedElementInitialiser {
	@Override
	public InstanceOfExpression instantiate();
	public default boolean setChild(InstanceOfExpression ioe, InstanceOfExpressionChild child) {
		if (child != null) {
			ioe.setChild(child);
			return ioe.getChild().equals(child);
		}
		return true;
	}
}
