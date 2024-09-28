package cipm.consistency.initialisers.eobject.java.operators;

import org.emftext.language.java.operators.Operator;

import cipm.consistency.initialisers.eobject.java.commons.ICommentableInitialiser;

public interface IOperatorInitialiser extends ICommentableInitialiser {
	@Override
	public Operator instantiate();

}
