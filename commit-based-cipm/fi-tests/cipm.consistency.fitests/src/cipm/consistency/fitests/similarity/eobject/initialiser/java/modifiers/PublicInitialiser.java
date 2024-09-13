package cipm.consistency.fitests.similarity.eobject.initialiser.java.modifiers;

import org.emftext.language.java.modifiers.ModifiersFactory;
import org.emftext.language.java.modifiers.Public;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class PublicInitialiser extends AbstractInitialiserBase implements IPublicInitialiser {
	@Override
	public IPublicInitialiser newInitialiser() {
		return new PublicInitialiser();
	}

	@Override
	public Public instantiate() {
		return ModifiersFactory.eINSTANCE.createPublic();
	}
}