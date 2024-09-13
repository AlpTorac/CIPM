package cipm.consistency.fitests.similarity.java.eobject.initialiser.operators;

import org.emftext.language.java.operators.Negate;

public interface INegateInitialiser extends IUnaryOperatorInitialiser {
	@Override
	public Negate instantiate();

}
