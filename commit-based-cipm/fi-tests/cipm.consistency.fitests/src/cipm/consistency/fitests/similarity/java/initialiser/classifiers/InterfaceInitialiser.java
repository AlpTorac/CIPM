package cipm.consistency.fitests.similarity.java.initialiser.classifiers;

import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.classifiers.Interface;

import cipm.consistency.fitests.similarity.java.initialiser.containers.ICompilationUnitInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.helper.ICompilationUnitContaineeInitialiser;

public class InterfaceInitialiser implements IInterfaceInitialiser, ICompilationUnitContaineeInitialiser {
	private ICompilationUnitInitialiser cuInit;
	
	@Override
	public ICompilationUnitInitialiser getCUInit() {
		return this.cuInit;
	}
	
	@Override
	public InterfaceInitialiser withCUInit(ICompilationUnitInitialiser cuInit) {
		this.cuInit = cuInit;
		return this;
	}
	
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
