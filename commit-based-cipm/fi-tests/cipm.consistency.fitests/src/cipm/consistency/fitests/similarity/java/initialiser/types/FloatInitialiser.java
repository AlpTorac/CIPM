package cipm.consistency.fitests.similarity.java.initialiser.types;

import org.emftext.language.java.types.Float;
import org.emftext.language.java.types.TypesFactory;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class FloatInitialiser extends AbstractInitialiserBase implements IFloatInitialiser {
	@Override
	public IFloatInitialiser newInitialiser() {
		return new FloatInitialiser();
	}

	@Override
	public Float instantiate() {
		return TypesFactory.eINSTANCE.createFloat();
	}
}