package cipm.consistency.fitests.similarity.java.initialiser.modifiers;

import org.emftext.language.java.modifiers.ModifiersFactory;
import org.emftext.language.java.modifiers.Static;

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