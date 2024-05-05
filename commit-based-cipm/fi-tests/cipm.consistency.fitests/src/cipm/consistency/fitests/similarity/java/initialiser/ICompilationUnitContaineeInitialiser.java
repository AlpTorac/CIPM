package cipm.consistency.fitests.similarity.java.initialiser;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.CompilationUnit;

import cipm.consistency.fitests.similarity.java.initialiser.containers.CompilationUnitInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.ICompilationUnitInitialiser;

public interface ICompilationUnitContaineeInitialiser extends EObjectInitialiser {
	/**
	 * Since {@link CompilationUnit} is the only root ({@link JavaRoot}) element,
	 * whose {@link ConcreteClassifier}s can be directly modified, providing a
	 * {@link ICompilationUnitInitialiser} instance enables
	 * {@link #minimalInitialisationWithContainer(EObject)}.
	 */
	public default ICompilationUnitInitialiser getCUInit() {
		return new CompilationUnitInitialiser();
	}
	
	@Override
	public ICompilationUnitContaineeInitialiser newInitialiser();
}
