package cipm.consistency.fitests.similarity.eobject.initialiser.java.classifiers;

import org.emftext.language.java.classifiers.ClassifiersFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

import org.emftext.language.java.classifiers.Class;

public class ClassInitialiser extends AbstractInitialiserBase implements IClassInitialiser {
	@Override
	public Class instantiate() {
		var fac = ClassifiersFactory.eINSTANCE;
		return fac.createClass();
	}

	@Override
	public ClassInitialiser newInitialiser() {
		return new ClassInitialiser();
	}
}
