package cipm.consistency.initialisers.eobject.java.expressions;

import org.emftext.language.java.expressions.AdditiveExpressionChild;

public interface IAdditiveExpressionChildInitialiser extends IShiftExpressionChildInitialiser {
	@Override
	public AdditiveExpressionChild instantiate();

}