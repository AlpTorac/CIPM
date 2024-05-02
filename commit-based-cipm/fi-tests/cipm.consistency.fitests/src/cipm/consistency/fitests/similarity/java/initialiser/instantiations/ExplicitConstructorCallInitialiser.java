package cipm.consistency.fitests.similarity.java.initialiser.instantiations;

import org.emftext.language.java.instantiations.ExplicitConstructorCall;
import org.emftext.language.java.instantiations.InstantiationsFactory;

public class ExplicitConstructorCallInitialiser implements IExplicitConstructorCallInitialiser {
	@Override
	public IExplicitConstructorCallInitialiser newInitialiser() {
		return new ExplicitConstructorCallInitialiser();
	}

	@Override
	public ExplicitConstructorCall instantiate() {
		return InstantiationsFactory.eINSTANCE.createExplicitConstructorCall();
	}
}