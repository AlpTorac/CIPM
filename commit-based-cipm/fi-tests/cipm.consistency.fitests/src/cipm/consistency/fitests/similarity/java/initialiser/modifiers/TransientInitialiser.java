package cipm.consistency.fitests.similarity.java.initialiser.modifiers;

import org.emftext.language.java.modifiers.ModifiersFactory;
import org.emftext.language.java.modifiers.Transient;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class TransientInitialiser extends AbstractInitialiserBase implements ITransientInitialiser {
	@Override
	public ITransientInitialiser newInitialiser() {
		return new TransientInitialiser();
	}

	@Override
	public Transient instantiate() {
		return ModifiersFactory.eINSTANCE.createTransient();
	}
}