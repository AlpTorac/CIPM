package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.expressions.InstanceOfExpression;
import org.emftext.language.java.expressions.InstanceOfExpressionChild;

public interface IInstanceOfExpressionInitialiser extends
	IEqualityExpressionChildInitialiser,
	ITypedElementInitialiser {
	
	public default void setChild(InstanceOfExpression ioe, InstanceOfExpressionChild child) {
		if (child != null) {
			ioe.setChild(child);
			assert ioe.getChild().equals(child);
		}
	}
}
