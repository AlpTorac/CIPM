package cipm.consistency.initialisers.emftext.expressions;

import org.emftext.language.java.expressions.LambdaBody;

import cipm.consistency.initialisers.emftext.commons.ICommentableInitialiser;

public interface ILambdaBodyInitialiser extends ICommentableInitialiser {
	@Override
	public LambdaBody instantiate();

}
