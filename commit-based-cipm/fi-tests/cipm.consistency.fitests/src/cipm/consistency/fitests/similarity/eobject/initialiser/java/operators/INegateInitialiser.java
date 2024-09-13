package cipm.consistency.fitests.similarity.eobject.initialiser.java.operators;

import org.emftext.language.java.operators.Negate;

public interface INegateInitialiser extends IUnaryOperatorInitialiser {
	@Override
	public Negate instantiate();

}
