package cipm.consistency.fitests.similarity.java.eobject.initialiser.modifiers;

import org.emftext.language.java.modifiers.ModifiersFactory;
import org.emftext.language.java.modifiers.Transitive;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class TransitiveInitialiser extends AbstractInitialiserBase implements ITransitiveInitialiser {
	@Override
	public ITransitiveInitialiser newInitialiser() {
		return new TransitiveInitialiser();
	}

	@Override
	public Transitive instantiate() {
		return ModifiersFactory.eINSTANCE.createTransitive();
	}
}