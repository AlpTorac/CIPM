package cipm.consistency.initialisers.emftext.operators;

import org.emftext.language.java.operators.Equal;

public interface IEqualInitialiser extends IEqualityOperatorInitialiser {
	@Override
	public Equal instantiate();

}
