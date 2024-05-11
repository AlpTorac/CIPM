package cipm.consistency.fitests.similarity.java.initialiser.classifiers;

import org.emftext.language.java.classifiers.ClassifiersFactory;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.ICompilationUnitInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.helper.ICompilationUnitContaineeInitialiser;

import org.emftext.language.java.classifiers.Class;

public class ClassInitialiser implements IClassInitialiser {
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
