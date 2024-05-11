package cipm.consistency.fitests.similarity.java.initialiser.classifiers;

import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.classifiers.Enumeration;

import cipm.consistency.fitests.similarity.java.initialiser.containers.ICompilationUnitInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.helper.ICompilationUnitContaineeInitialiser;

public class EnumerationInitialiser implements IEnumerationInitialiser {
	@Override
	public Enumeration instantiate() {
		var fac = ClassifiersFactory.eINSTANCE;
		return fac.createEnumeration();
	}

	@Override
	public EnumerationInitialiser newInitialiser() {
		return new EnumerationInitialiser();
	}
}
