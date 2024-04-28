package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.classifiers.Enumeration;

import cipm.consistency.fitests.similarity.java.initialiser.IEnumerationInitialiser;

public class EnumerationInitialiser extends ConcreteClassifierInitialiser implements IEnumerationInitialiser {
	@Override
	public Enumeration instantiate() {
		var fac = ClassifiersFactory.eINSTANCE;
		return fac.createEnumeration();
	}
}
