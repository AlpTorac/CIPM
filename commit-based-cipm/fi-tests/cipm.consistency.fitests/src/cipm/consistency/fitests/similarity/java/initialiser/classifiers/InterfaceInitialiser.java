package cipm.consistency.fitests.similarity.java.initialiser.classifiers;

import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.classifiers.Interface;

import cipm.consistency.fitests.similarity.java.initialiser.ConcreteClassifierInitialiser;

public class InterfaceInitialiser extends ConcreteClassifierInitialiser implements IInterfaceInitialiser {
	@Override
	public Interface instantiate() {
		var fac = ClassifiersFactory.eINSTANCE;
		return fac.createInterface();
	}
	
	@Override
	public ConcreteClassifierInitialiser newInitialiser() {
		return new InterfaceInitialiser();
	}
}
