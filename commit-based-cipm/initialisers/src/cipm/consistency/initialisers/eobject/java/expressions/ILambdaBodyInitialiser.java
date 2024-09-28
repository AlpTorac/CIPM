package cipm.consistency.initialisers.eobject.java.expressions;

import org.emftext.language.java.expressions.LambdaBody;

import cipm.consistency.initialisers.eobject.java.commons.ICommentableInitialiser;

public interface ILambdaBodyInitialiser extends ICommentableInitialiser {
	@Override
	public LambdaBody instantiate();

}
