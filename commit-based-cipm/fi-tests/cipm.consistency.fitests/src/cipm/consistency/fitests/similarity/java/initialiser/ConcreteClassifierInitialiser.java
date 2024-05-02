package cipm.consistency.fitests.similarity.java.initialiser;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.CompilationUnit;

import cipm.consistency.fitests.similarity.java.initialiser.containers.CompilationUnitInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.ICompilationUnitInitialiser;

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
