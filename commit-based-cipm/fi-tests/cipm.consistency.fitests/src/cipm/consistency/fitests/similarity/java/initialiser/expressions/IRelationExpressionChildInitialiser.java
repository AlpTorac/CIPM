package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.RelationExpressionChild;

public interface IRelationExpressionChildInitialiser extends IInstanceOfExpressionChildInitialiser {
    @Override
    public RelationExpressionChild instantiate();
	
}