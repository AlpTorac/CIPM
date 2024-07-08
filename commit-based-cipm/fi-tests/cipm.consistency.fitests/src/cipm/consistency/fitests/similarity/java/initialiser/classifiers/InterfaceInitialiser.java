package cipm.consistency.fitests.similarity.java.initialiser.classifiers;

import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.classifiers.Interface;

import cipm.consistency.fitests.similarity.java.initialiser.containers.ICompilationUnitInitialiser;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class InterfaceInitialiser extends AbstractInitialiserBase implements IInterfaceInitialiser {
	@Override
	public Interface instantiate() {
		var fac = ClassifiersFactory.eINSTANCE;
		return fac.createInterface();
	}
	
	@Override
	public InterfaceInitialiser newInitialiser() {
		return new InterfaceInitialiser();
	}
}
