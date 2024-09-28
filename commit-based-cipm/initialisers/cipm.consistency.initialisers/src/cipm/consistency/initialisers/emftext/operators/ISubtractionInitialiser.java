package cipm.consistency.initialisers.emftext.operators;

import org.emftext.language.java.operators.Subtraction;

public interface ISubtractionInitialiser extends IAdditiveOperatorInitialiser {
	@Override
	public Subtraction instantiate();

}
