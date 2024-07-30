package cipm.consistency.fitests.similarity.java.initialiser.operators;

import org.emftext.language.java.operators.Subtraction;

public interface ISubtractionInitialiser extends IAdditiveOperatorInitialiser {
	@Override
	public Subtraction instantiate();

}
