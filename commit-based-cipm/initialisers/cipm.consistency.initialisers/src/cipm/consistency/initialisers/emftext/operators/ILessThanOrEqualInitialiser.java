package cipm.consistency.initialisers.emftext.operators;

import org.emftext.language.java.operators.LessThanOrEqual;

public interface ILessThanOrEqualInitialiser extends IRelationOperatorInitialiser {
	@Override
	public LessThanOrEqual instantiate();

}
