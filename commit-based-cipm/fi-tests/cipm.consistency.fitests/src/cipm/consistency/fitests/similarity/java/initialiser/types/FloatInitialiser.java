package cipm.consistency.fitests.similarity.java.initialiser.types;

import org.emftext.language.java.types.Float;
import org.emftext.language.java.types.TypesFactory;

public class FloatInitialiser implements IFloatInitialiser {
	@Override
	public IFloatInitialiser newInitialiser() {
		return new FloatInitialiser();
	}
	
	@Override
	public Float instantiate() {
		return TypesFactory.eINSTANCE.createFloat();
	}
}