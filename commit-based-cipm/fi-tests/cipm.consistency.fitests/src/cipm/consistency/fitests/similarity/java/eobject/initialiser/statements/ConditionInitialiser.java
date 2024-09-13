package cipm.consistency.fitests.similarity.java.eobject.initialiser.statements;

import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.StatementsFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class ConditionInitialiser extends AbstractInitialiserBase implements IConditionInitialiser {
	@Override
	public IConditionInitialiser newInitialiser() {
		return new ConditionInitialiser();
	}

	@Override
	public Condition instantiate() {
		return StatementsFactory.eINSTANCE.createCondition();
	}
}