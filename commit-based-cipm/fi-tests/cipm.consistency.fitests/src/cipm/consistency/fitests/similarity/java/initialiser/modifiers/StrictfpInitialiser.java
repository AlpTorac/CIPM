package cipm.consistency.fitests.similarity.java.initialiser.modifiers;

import org.emftext.language.java.modifiers.ModifiersFactory;
import org.emftext.language.java.modifiers.Strictfp;

public class StrictfpInitialiser implements IStrictfpInitialiser {
	@Override
	public IStrictfpInitialiser newInitialiser() {
		return new StrictfpInitialiser();
	}

	@Override
	public Strictfp instantiate() {
		return ModifiersFactory.eINSTANCE.createStrictfp();
	}
}