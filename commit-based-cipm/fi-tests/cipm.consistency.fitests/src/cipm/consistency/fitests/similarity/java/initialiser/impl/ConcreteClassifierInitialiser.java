package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.CompilationUnit;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.ICompilationUnitInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IConcreteClassifierInitialiser;

public abstract class ConcreteClassifierInitialiser implements IConcreteClassifierInitialiser {
	/**
	 * Since {@link CompilationUnit} is the only root ({@link JavaRoot}) element,
	 * whose {@link ConcreteClassifier}s can be directly modified, providing a
	 * {@link ICompilationUnitInitialiser} instance enables
	 * {@link #minimalInitialisationWithContainer(EObject)}.
	 */
	protected ICompilationUnitInitialiser getCUInit() {
		return new CompilationUnitInitialiser();
	}
	
	/**
	 * {@inheritDoc}
	 * <br><br>
	 * Creates a minimal {@link CompilationUnit} and puts obj in it.
	 * @param obj: A given {@link ConcreteClassifier} instance
	 */
	@Override
	public EObject minimalInitialisationWithContainer(EObject obj) {
		var castedO = (ConcreteClassifier) obj;
		this.minimalInitialisation(castedO);
		
		var cuInit = this.getCUInit();
		CompilationUnit unit = cuInit.instantiate();
		
		cuInit.minimalInitialisation(unit);
		cuInit.addClassifier(unit, castedO);
		
		return unit;
	}
	
	@Override
	public abstract ConcreteClassifierInitialiser newInitialiser();
}
