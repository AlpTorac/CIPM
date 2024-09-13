package cipm.consistency.fitests.similarity.eobject.initialiser.java.expressions;

import org.emftext.language.java.expressions.LambdaBody;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.commons.ICommentableInitialiser;

public interface ILambdaBodyInitialiser extends ICommentableInitialiser {
	@Override
	public LambdaBody instantiate();

}
