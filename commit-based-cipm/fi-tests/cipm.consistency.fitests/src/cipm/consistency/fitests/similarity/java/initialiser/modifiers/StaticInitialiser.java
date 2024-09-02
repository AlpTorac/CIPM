package cipm.consistency.fitests.similarity.java.initialiser.modifiers;

import org.emftext.language.java.modifiers.ModifiersFactory;
import org.emftext.language.java.modifiers.Static;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class StaticInitialiser extends AbstractInitialiserBase implements IStaticInitialiser {
	@Override
	public IStaticInitialiser newInitialiser() {
		return new StaticInitialiser();
	}

	@Override
	public Static instantiate() {
		return ModifiersFactory.eINSTANCE.createStatic();
	}
}