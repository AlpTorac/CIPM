package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.AdditiveExpressionChild;

public interface IAdditiveExpressionChildInitialiser extends IShiftExpressionChildInitialiser {
	@Override
	public AdditiveExpressionChild instantiate();

}