package cipm.consistency.initialisers.emftext.operators;

import org.emftext.language.java.operators.NotEqual;

public interface INotEqualInitialiser extends IEqualityOperatorInitialiser {
	@Override
	public NotEqual instantiate();

}