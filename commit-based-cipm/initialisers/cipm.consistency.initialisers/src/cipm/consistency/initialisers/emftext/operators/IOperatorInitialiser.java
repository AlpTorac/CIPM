package cipm.consistency.initialisers.emftext.operators;

import org.emftext.language.java.operators.Operator;

import cipm.consistency.initialisers.emftext.commons.ICommentableInitialiser;

public interface IOperatorInitialiser extends ICommentableInitialiser {
	@Override
	public Operator instantiate();

}
