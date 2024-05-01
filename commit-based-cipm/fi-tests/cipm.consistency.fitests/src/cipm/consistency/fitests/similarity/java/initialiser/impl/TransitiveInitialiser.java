package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.modifiers.ModifiersFactory;
import org.emftext.language.java.modifiers.Transitive;

import cipm.consistency.fitests.similarity.java.initialiser.ITransitiveInitialiser;

public class TransitiveInitialiser implements ITransitiveInitialiser {
	@Override
	public ITransitiveInitialiser newInitialiser() {
		return new TransitiveInitialiser();
	}

	@Override
	public Transitive instantiate() {
		return ModifiersFactory.eINSTANCE.createTransitive();
	}
}