package cipm.consistency.fitests.similarity.java.initialiser.modifiers;

import org.emftext.language.java.modifiers.Final;
import org.emftext.language.java.modifiers.ModifiersFactory;

public class FinalInitialiser implements IFinalInitialiser {
	@Override
	public IFinalInitialiser newInitialiser() {
		return new FinalInitialiser();
	}

	@Override
	public Final instantiate() {
		return ModifiersFactory.eINSTANCE.createFinal();
	}
}