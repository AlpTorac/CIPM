package cipm.consistency.initialisers.emftext.operators;

import org.emftext.language.java.operators.LessThan;

public interface ILessThanInitialiser extends IRelationOperatorInitialiser {
	@Override
	public LessThan instantiate();

}
