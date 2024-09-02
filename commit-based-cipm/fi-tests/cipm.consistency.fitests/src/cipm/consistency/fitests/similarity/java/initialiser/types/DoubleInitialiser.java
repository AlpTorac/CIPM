package cipm.consistency.fitests.similarity.java.initialiser.types;

import org.emftext.language.java.types.Double;
import org.emftext.language.java.types.TypesFactory;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class DoubleInitialiser extends AbstractInitialiserBase implements IDoubleInitialiser {
	@Override
	public IDoubleInitialiser newInitialiser() {
		return new DoubleInitialiser();
	}

	@Override
	public Double instantiate() {
		return TypesFactory.eINSTANCE.createDouble();
	}
}