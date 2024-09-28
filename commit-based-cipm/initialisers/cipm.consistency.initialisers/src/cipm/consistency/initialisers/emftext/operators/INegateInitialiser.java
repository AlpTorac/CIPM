package cipm.consistency.initialisers.emftext.operators;

import org.emftext.language.java.operators.Negate;

public interface INegateInitialiser extends IUnaryOperatorInitialiser {
	@Override
	public Negate instantiate();

}
