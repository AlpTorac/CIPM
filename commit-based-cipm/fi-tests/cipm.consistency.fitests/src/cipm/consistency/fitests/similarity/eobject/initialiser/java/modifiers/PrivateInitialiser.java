package cipm.consistency.fitests.similarity.eobject.initialiser.java.modifiers;

import org.emftext.language.java.modifiers.ModifiersFactory;
import org.emftext.language.java.modifiers.Private;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class PrivateInitialiser extends AbstractInitialiserBase implements IPrivateInitialiser {
	@Override
	public IPrivateInitialiser newInitialiser() {
		return new PrivateInitialiser();
	}

	@Override
	public Private instantiate() {
		return ModifiersFactory.eINSTANCE.createPrivate();
	}
}