package cipm.consistency.fitests.similarity.java.initialiser.types;

import org.emftext.language.java.types.Double;
import org.emftext.language.java.types.TypesFactory;

public class DoubleInitialiser implements IDoubleInitialiser {
	@Override
	public IDoubleInitialiser newInitialiser() {
		return new DoubleInitialiser();
	}
	
	@Override
	public Double instantiate() {
		return TypesFactory.eINSTANCE.createDouble();
	}
}