package cipm.consistency.initialisers.eobject.java.instantiations;

import org.emftext.language.java.instantiations.InstantiationsFactory;
import org.emftext.language.java.instantiations.NewConstructorCall;

import cipm.consistency.initialisers.AbstractInitialiserBase;

public class NewConstructorCallInitialiser extends AbstractInitialiserBase implements INewConstructorCallInitialiser {
	@Override
	public INewConstructorCallInitialiser newInitialiser() {
		return new NewConstructorCallInitialiser();
	}

	@Override
	public NewConstructorCall instantiate() {
		return InstantiationsFactory.eINSTANCE.createNewConstructorCall();
	}
}