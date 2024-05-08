package cipm.consistency.fitests.similarity.java.initialiser.types;

import org.emftext.language.java.types.Int;
import org.emftext.language.java.types.TypesFactory;

public class IntInitialiser implements IIntInitialiser {
	@Override
	public IIntInitialiser newInitialiser() {
		return new IntInitialiser();
	}
	
	@Override
	public Int instantiate() {
		return TypesFactory.eINSTANCE.createInt();
	}
}