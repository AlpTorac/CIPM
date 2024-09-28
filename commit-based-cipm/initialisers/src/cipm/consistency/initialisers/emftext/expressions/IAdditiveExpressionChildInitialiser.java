package cipm.consistency.initialisers.emftext.expressions;

import org.emftext.language.java.expressions.AdditiveExpressionChild;

public interface IAdditiveExpressionChildInitialiser extends IShiftExpressionChildInitialiser {
	@Override
	public AdditiveExpressionChild instantiate();

}