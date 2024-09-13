package cipm.consistency.fitests.similarity.java.eobject.initialiser.expressions;

import org.emftext.language.java.expressions.LambdaBody;
import org.emftext.language.java.expressions.LambdaExpression;
import org.emftext.language.java.expressions.LambdaParameters;

public interface ILambdaExpressionInitialiser extends IExpressionInitialiser {
	@Override
	public LambdaExpression instantiate();

	public default boolean setBody(LambdaExpression le, LambdaBody body) {
		if (body != null) {
			le.setBody(body);
			return le.getBody().equals(body);
		}
		return true;
	}

	public default boolean setParameters(LambdaExpression le, LambdaParameters param) {
		if (param != null) {
			le.setParameters(param);
			return le.getParameters().equals(param);
		}
		return true;
	}
}
