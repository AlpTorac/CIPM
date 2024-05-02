package cipm.consistency.fitests.similarity.java.initialiser.modifiers;

import org.emftext.language.java.modifiers.ModifiersFactory;
import org.emftext.language.java.modifiers.Native;

public class NativeInitialiser implements INativeInitialiser {
	@Override
	public INativeInitialiser newInitialiser() {
		return new NativeInitialiser();
	}

	@Override
	public Native instantiate() {
		return ModifiersFactory.eINSTANCE.createNative();
	}
}