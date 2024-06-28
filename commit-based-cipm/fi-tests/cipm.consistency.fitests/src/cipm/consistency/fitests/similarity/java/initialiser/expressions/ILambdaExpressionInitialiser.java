package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.LambdaBody;
import org.emftext.language.java.expressions.LambdaExpression;
import org.emftext.language.java.expressions.LambdaParameters;

public interface ILambdaExpressionInitialiser extends IExpressionInitialiser {
	public default void setBody(LambdaExpression le, LambdaBody lb) {
		if (lb != null) {
			le.setBody(lb);
			assert le.getBody().equals(lb);
		}
	}
	
	public default void setParameters(LambdaExpression le, LambdaParameters lp) {
		if (lp != null) {
			le.setParameters(lp);
			assert le.getParameters().equals(lp);
		}
	}
}
