package cipm.consistency.fitests.similarity.java.eobject.initialiser.modifiers;

import org.emftext.language.java.modifiers.ModifiersFactory;
import org.emftext.language.java.modifiers.Volatile;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class VolatileInitialiser extends AbstractInitialiserBase implements IVolatileInitialiser {
	@Override
	public IVolatileInitialiser newInitialiser() {
		return new VolatileInitialiser();
	}

	@Override
	public Volatile instantiate() {
		return ModifiersFactory.eINSTANCE.createVolatile();
	}
}