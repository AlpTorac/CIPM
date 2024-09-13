package cipm.consistency.fitests.similarity.java.eobject.initialiser.modifiers;

import org.emftext.language.java.modifiers.Final;
import org.emftext.language.java.modifiers.ModifiersFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class FinalInitialiser extends AbstractInitialiserBase implements IFinalInitialiser {
	@Override
	public IFinalInitialiser newInitialiser() {
		return new FinalInitialiser();
	}

	@Override
	public Final instantiate() {
		return ModifiersFactory.eINSTANCE.createFinal();
	}
}