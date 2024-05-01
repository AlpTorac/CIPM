package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.modifiers.ModifiersFactory;
import org.emftext.language.java.modifiers.Static;

import cipm.consistency.fitests.similarity.java.initialiser.IStaticInitialiser;

public class StaticInitialiser implements IStaticInitialiser {
	@Override
	public IStaticInitialiser newInitialiser() {
		return new StaticInitialiser();
	}

	@Override
	public Static instantiate() {
		return ModifiersFactory.eINSTANCE.createStatic();
	}
}