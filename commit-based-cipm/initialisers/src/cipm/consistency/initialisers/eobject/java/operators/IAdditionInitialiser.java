package cipm.consistency.initialisers.eobject.java.operators;

import org.emftext.language.java.operators.Addition;

public interface IAdditionInitialiser extends IAdditiveOperatorInitialiser {
	@Override
	public Addition instantiate();

}
