package cipm.consistency.fitests.similarity.java.initialiser.types;

import org.emftext.language.java.types.Int;
import org.emftext.language.java.types.TypesFactory;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class IntInitialiser extends AbstractInitialiserBase implements IIntInitialiser {
	@Override
	public IIntInitialiser newInitialiser() {
		return new IntInitialiser();
	}
	
	@Override
	public Int instantiate() {
		return TypesFactory.eINSTANCE.createInt();
	}
}