package cipm.consistency.fitests.similarity.java.initialiser.modifiers;

import org.emftext.language.java.modifiers.ModifiersFactory;
import org.emftext.language.java.modifiers.Synchronized;

public class SynchronizedInitialiser implements ISynchronizedInitialiser {
	@Override
	public ISynchronizedInitialiser newInitialiser() {
		return new SynchronizedInitialiser();
	}

	@Override
	public Synchronized instantiate() {
		return ModifiersFactory.eINSTANCE.createSynchronized();
	}
}