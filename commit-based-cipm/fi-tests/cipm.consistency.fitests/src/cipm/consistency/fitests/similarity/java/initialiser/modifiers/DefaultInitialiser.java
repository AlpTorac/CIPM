package cipm.consistency.fitests.similarity.java.initialiser.modifiers;

import org.emftext.language.java.modifiers.Default;
import org.emftext.language.java.modifiers.ModifiersFactory;

public class DefaultInitialiser implements IDefaultInitialiser {
	@Override
	public IDefaultInitialiser newInitialiser() {
		return new DefaultInitialiser();
	}

	@Override
	public Default instantiate() {
		return ModifiersFactory.eINSTANCE.createDefault();
	}
}