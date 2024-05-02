package cipm.consistency.fitests.similarity.java.initialiser.modifiers;

import org.emftext.language.java.modifiers.ModifiersFactory;
import org.emftext.language.java.modifiers.Volatile;

public class VolatileInitialiser implements IVolatileInitialiser {
	@Override
	public IVolatileInitialiser newInitialiser() {
		return new VolatileInitialiser();
	}

	@Override
	public Volatile instantiate() {
		return ModifiersFactory.eINSTANCE.createVolatile();
	}
}