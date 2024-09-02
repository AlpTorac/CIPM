package cipm.consistency.fitests.similarity.java.initialiser.types;

import org.emftext.language.java.types.TypesFactory;
import org.emftext.language.java.types.Void;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class VoidInitialiser extends AbstractInitialiserBase implements IVoidInitialiser {
	@Override
	public IVoidInitialiser newInitialiser() {
		return new VoidInitialiser();
	}

	@Override
	public Void instantiate() {
		return TypesFactory.eINSTANCE.createVoid();
	}
}