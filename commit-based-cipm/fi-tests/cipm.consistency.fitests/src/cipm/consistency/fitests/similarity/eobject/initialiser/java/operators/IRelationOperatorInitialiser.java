package cipm.consistency.fitests.similarity.eobject.initialiser.java.operators;

import org.emftext.language.java.operators.RelationOperator;

public interface IRelationOperatorInitialiser extends IOperatorInitialiser {
	@Override
	public RelationOperator instantiate();

}