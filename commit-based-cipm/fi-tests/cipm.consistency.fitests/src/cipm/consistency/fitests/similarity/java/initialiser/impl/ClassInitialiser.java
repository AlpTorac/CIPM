package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.classifiers.ClassifiersFactory;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IClassInitialiser;

import org.emftext.language.java.classifiers.Class;

public class ClassInitialiser extends ConcreteClassifierInitialiser implements IClassInitialiser {
	@Override
	public Class instantiate() {
		var fac = ClassifiersFactory.eINSTANCE;
		return fac.createClass();
	}

	@Override
	public ConcreteClassifierInitialiser newInitialiser() {
		return new ClassInitialiser();
	}
}
