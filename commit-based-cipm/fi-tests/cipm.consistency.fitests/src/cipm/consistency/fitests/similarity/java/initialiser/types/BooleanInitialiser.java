package cipm.consistency.fitests.similarity.java.initialiser.types;

import org.emftext.language.java.types.Boolean;
import org.emftext.language.java.types.TypesFactory;

public class BooleanInitialiser implements IBooleanInitialiser {
	@Override
	public IBooleanInitialiser newInitialiser() {
		return new BooleanInitialiser();
	}
	
	@Override
	public Boolean instantiate() {
		return TypesFactory.eINSTANCE.createBoolean();
	}
}