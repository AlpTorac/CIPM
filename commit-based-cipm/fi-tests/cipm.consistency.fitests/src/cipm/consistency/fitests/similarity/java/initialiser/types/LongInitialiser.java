package cipm.consistency.fitests.similarity.java.initialiser.types;

import org.emftext.language.java.types.Long;
import org.emftext.language.java.types.TypesFactory;

public class LongInitialiser implements ILongInitialiser {
	@Override
	public ILongInitialiser newInitialiser() {
		return new LongInitialiser();
	}
	
	@Override
	public Long instantiate() {
		return TypesFactory.eINSTANCE.createLong();
	}
}