package cipm.consistency.fitests.similarity.java.initialiser;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.CompilationUnit;

public abstract class ConcreteClassifierInitialiser	implements ICompilationUnitContaineeInitialiser {
	/**
	 * {@inheritDoc}
	 * <br><br>
	 * Creates a minimal {@link CompilationUnit} and puts obj in it.
	 * @param obj: A given {@link ConcreteClassifier} instance
	 */
	@Override
	public CompilationUnit minimalInitialisationWithContainer(EObject obj) {
		var castedO = (ConcreteClassifier) obj;
		this.minimalInitialisation(castedO);
		
		var cuInit = this.getCUInit();
		CompilationUnit unit = cuInit.instantiate();
		
		cuInit.minimalInitialisation(unit);
		cuInit.addClassifier(unit, castedO);
		
		return unit;
	}
}
