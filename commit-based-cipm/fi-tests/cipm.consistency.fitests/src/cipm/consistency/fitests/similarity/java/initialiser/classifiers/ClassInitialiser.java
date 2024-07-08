package cipm.consistency.fitests.similarity.java.initialiser.classifiers;

import org.emftext.language.java.classifiers.ClassifiersFactory;

import cipm.consistency.fitests.similarity.java.initialiser.containers.ICompilationUnitInitialiser;

import org.emftext.language.java.classifiers.Class;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

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
