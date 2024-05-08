package cipm.consistency.fitests.similarity.java.initialiser.types;

import org.emftext.language.java.types.TypesFactory;
import org.emftext.language.java.types.Void;

public class VoidInitialiser implements IVoidInitialiser {
	@Override
	public IVoidInitialiser newInitialiser() {
		return new VoidInitialiser();
	}
	
	@Override
	public Void instantiate() {
		return TypesFactory.eINSTANCE.createVoid();
	}
}