package cipm.consistency.fitests.similarity.java.initialiser.modifiers;

import org.emftext.language.java.modifiers.ModifiersFactory;
import org.emftext.language.java.modifiers.Transient;

public class TransientInitialiser implements ITransientInitialiser {
	@Override
	public ITransientInitialiser newInitialiser() {
		return new TransientInitialiser();
	}

	@Override
	public Transient instantiate() {
		return ModifiersFactory.eINSTANCE.createTransient();
	}
}