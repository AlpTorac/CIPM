package cipm.consistency.fitests.similarity.java.eobject.initialiser.operators;

import org.emftext.language.java.operators.RelationOperator;

public interface IRelationOperatorInitialiser extends IOperatorInitialiser {
	@Override
	public RelationOperator instantiate();

}