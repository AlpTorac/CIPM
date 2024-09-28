package cipm.consistency.initialisers.eobject.java.operators;

import org.emftext.language.java.operators.RelationOperator;

public interface IRelationOperatorInitialiser extends IOperatorInitialiser {
	@Override
	public RelationOperator instantiate();

}