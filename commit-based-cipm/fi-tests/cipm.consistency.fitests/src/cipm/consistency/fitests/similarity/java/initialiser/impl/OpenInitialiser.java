package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.modifiers.ModifiersFactory;
import org.emftext.language.java.modifiers.Open;

import cipm.consistency.fitests.similarity.java.initialiser.IOpenInitialiser;

public class OpenInitialiser implements IOpenInitialiser {
	@Override
	public IOpenInitialiser newInitialiser() {
		return new OpenInitialiser();
	}

	@Override
	public Open instantiate() {
		return ModifiersFactory.eINSTANCE.createOpen();
	}
}