package cipm.consistency.fitests.similarity.eobject.initialiser.java.modifiers;

import org.emftext.language.java.modifiers.Abstract;
import org.emftext.language.java.modifiers.ModifiersFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class AbstractInitialiser extends AbstractInitialiserBase implements IAbstractInitialiser {
	@Override
	public IAbstractInitialiser newInitialiser() {
		return new AbstractInitialiser();
	}

	@Override
	public Abstract instantiate() {
		return ModifiersFactory.eINSTANCE.createAbstract();
	}
}