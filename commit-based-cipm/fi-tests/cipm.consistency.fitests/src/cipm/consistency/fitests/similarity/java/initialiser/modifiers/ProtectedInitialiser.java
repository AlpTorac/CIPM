package cipm.consistency.fitests.similarity.java.initialiser.modifiers;

import org.emftext.language.java.modifiers.ModifiersFactory;
import org.emftext.language.java.modifiers.Protected;

public class ProtectedInitialiser implements IProtectedInitialiser {
	@Override
	public IProtectedInitialiser newInitialiser() {
		return new ProtectedInitialiser();
	}

	@Override
	public Protected instantiate() {
		return ModifiersFactory.eINSTANCE.createProtected();
	}
}