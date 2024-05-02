package cipm.consistency.fitests.similarity.java.initialiser.classifiers;

import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.classifiers.Enumeration;

import cipm.consistency.fitests.similarity.java.initialiser.ConcreteClassifierInitialiser;

public class EnumerationInitialiser extends ConcreteClassifierInitialiser implements IEnumerationInitialiser {
	@Override
	public Enumeration instantiate() {
		var fac = ClassifiersFactory.eINSTANCE;
		return fac.createEnumeration();
	}

	@Override
	public ConcreteClassifierInitialiser newInitialiser() {
		return new EnumerationInitialiser();
	}
}
