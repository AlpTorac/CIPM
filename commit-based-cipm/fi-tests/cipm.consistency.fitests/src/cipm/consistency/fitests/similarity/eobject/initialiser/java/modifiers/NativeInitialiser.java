package cipm.consistency.fitests.similarity.eobject.initialiser.java.modifiers;

import org.emftext.language.java.modifiers.ModifiersFactory;
import org.emftext.language.java.modifiers.Native;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class NativeInitialiser extends AbstractInitialiserBase implements INativeInitialiser {
	@Override
	public INativeInitialiser newInitialiser() {
		return new NativeInitialiser();
	}

	@Override
	public Native instantiate() {
		return ModifiersFactory.eINSTANCE.createNative();
	}
}