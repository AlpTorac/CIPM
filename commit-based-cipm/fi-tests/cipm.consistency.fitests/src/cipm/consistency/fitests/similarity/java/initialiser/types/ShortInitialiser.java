package cipm.consistency.fitests.similarity.java.initialiser.types;

import org.emftext.language.java.types.Short;
import org.emftext.language.java.types.TypesFactory;

public class ShortInitialiser implements IShortInitialiser {
	@Override
	public IShortInitialiser newInitialiser() {
		return new ShortInitialiser();
	}
	
	@Override
	public Short instantiate() {
		return TypesFactory.eINSTANCE.createShort();
	}
}