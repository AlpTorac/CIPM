package cipm.consistency.fitests.similarity.java.eobject.initialiser.operators;

import org.emftext.language.java.operators.Operator;

import cipm.consistency.fitests.similarity.java.eobject.initialiser.commons.ICommentableInitialiser;

public interface IOperatorInitialiser extends ICommentableInitialiser {
	@Override
	public Operator instantiate();

}
