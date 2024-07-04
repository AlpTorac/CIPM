package cipm.consistency.fitests.similarity.java.initialiser.operators;

import cipm.consistency.fitests.similarity.java.initialiser.commons.ICommentableInitialiser;

import org.emftext.language.java.operators.Operator;

public interface IOperatorInitialiser extends ICommentableInitialiser {
    @Override
    public Operator instantiate();

}
