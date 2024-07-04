package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.LambdaBody;
import org.emftext.language.java.expressions.LambdaExpression;
import org.emftext.language.java.expressions.LambdaParameters;

public interface ILambdaExpressionInitialiser extends IExpressionInitialiser {
    @Override
    public LambdaExpression instantiate();
	public default boolean setBody(LambdaExpression le, LambdaBody lb) {
		if (lb != null) {
			le.setBody(lb);
			return le.getBody().equals(lb);
		}
		return true;
	}
	
	public default boolean setParameters(LambdaExpression le, LambdaParameters lp) {
		if (lp != null) {
			le.setParameters(lp);
			return le.getParameters().equals(lp);
		}
		return true;
	}
}
