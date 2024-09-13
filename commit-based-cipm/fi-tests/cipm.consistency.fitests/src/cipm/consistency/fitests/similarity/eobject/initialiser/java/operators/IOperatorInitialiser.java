package cipm.consistency.fitests.similarity.eobject.initialiser.java.operators;

import org.emftext.language.java.operators.Operator;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.commons.ICommentableInitialiser;

public interface IOperatorInitialiser extends ICommentableInitialiser {
	@Override
	public Operator instantiate();

}
