package cipm.consistency.fitests.similarity.java.eobject.initialiser.expressions;

import org.emftext.language.java.expressions.LambdaBody;

import cipm.consistency.fitests.similarity.java.eobject.initialiser.commons.ICommentableInitialiser;

public interface ILambdaBodyInitialiser extends ICommentableInitialiser {
	@Override
	public LambdaBody instantiate();

}
