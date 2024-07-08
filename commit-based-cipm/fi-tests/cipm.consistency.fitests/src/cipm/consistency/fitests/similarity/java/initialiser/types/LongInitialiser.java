package cipm.consistency.fitests.similarity.java.initialiser.types;

import org.emftext.language.java.types.Long;
import org.emftext.language.java.types.TypesFactory;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class LongInitialiser extends AbstractInitialiserBase implements ILongInitialiser {
	@Override
	public ILongInitialiser newInitialiser() {
		return new LongInitialiser();
	}
	
	@Override
	public Long instantiate() {
		return TypesFactory.eINSTANCE.createLong();
	}
}