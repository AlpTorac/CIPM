package cipm.consistency.initialisers.eobject.java.operators;

import org.emftext.language.java.operators.AdditiveOperator;

public interface IAdditiveOperatorInitialiser extends IOperatorInitialiser {
	@Override
	public AdditiveOperator instantiate();

}
