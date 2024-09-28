package cipm.consistency.initialisers.emftext.operators;

import org.emftext.language.java.operators.Multiplication;

public interface IMultiplicationInitialiser extends IMultiplicativeOperatorInitialiser {
	@Override
	public Multiplication instantiate();

}
