package cipm.consistency.initialisers.emftext.operators;

import org.emftext.language.java.operators.GreaterThanOrEqual;

public interface IGreaterThanOrEqualInitialiser extends IRelationOperatorInitialiser {
	@Override
	public GreaterThanOrEqual instantiate();

}
