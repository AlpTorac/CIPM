package cipm.consistency.initialisers.eobject.java.expressions;

import org.emftext.language.java.expressions.RelationExpressionChild;

public interface IRelationExpressionChildInitialiser extends IInstanceOfExpressionChildInitialiser {
	@Override
	public RelationExpressionChild instantiate();

}