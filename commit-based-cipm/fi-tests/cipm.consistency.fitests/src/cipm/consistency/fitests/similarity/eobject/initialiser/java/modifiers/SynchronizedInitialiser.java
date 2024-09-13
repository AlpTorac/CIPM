package cipm.consistency.fitests.similarity.eobject.initialiser.java.modifiers;

import org.emftext.language.java.modifiers.ModifiersFactory;
import org.emftext.language.java.modifiers.Synchronized;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class SynchronizedInitialiser extends AbstractInitialiserBase implements ISynchronizedInitialiser {
	@Override
	public ISynchronizedInitialiser newInitialiser() {
		return new SynchronizedInitialiser();
	}

	@Override
	public Synchronized instantiate() {
		return ModifiersFactory.eINSTANCE.createSynchronized();
	}
}