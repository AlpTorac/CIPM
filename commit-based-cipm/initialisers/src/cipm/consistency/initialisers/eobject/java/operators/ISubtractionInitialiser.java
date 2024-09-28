package cipm.consistency.initialisers.eobject.java.operators;

import org.emftext.language.java.operators.Subtraction;

public interface ISubtractionInitialiser extends IAdditiveOperatorInitialiser {
	@Override
	public Subtraction instantiate();

}
