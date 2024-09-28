package cipm.consistency.initialisers.emftext.operators;

import org.emftext.language.java.operators.Addition;

public interface IAdditionInitialiser extends IAdditiveOperatorInitialiser {
	@Override
	public Addition instantiate();

}
