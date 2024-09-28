package cipm.consistency.initialisers.emftext.expressions;

import org.emftext.language.java.expressions.RelationExpressionChild;

public interface IRelationExpressionChildInitialiser extends IInstanceOfExpressionChildInitialiser {
	@Override
	public RelationExpressionChild instantiate();

}