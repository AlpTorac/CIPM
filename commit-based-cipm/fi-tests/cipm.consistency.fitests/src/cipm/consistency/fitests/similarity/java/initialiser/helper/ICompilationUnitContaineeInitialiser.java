package cipm.consistency.fitests.similarity.java.initialiser.helper;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.CompilationUnit;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.CompilationUnitInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.ICompilationUnitInitialiser;

public interface ICompilationUnitContaineeInitialiser extends EObjectInitialiser {
	/**
	 * Since {@link CompilationUnit} is the only root ({@link JavaRoot}) element,
	 * whose {@link ConcreteClassifier}s can be directly modified, providing a
	 * {@link ICompilationUnitInitialiser} instance enables
	 * {@link #minimalInitialisationWithContainer(EObject)}.
	 */
	public default ICompilationUnitInitialiser getDefaultCUInit() {
		return new CompilationUnitInitialiser();
	}
	
	public ICompilationUnitInitialiser getCUInit();
	public ICompilationUnitContaineeInitialiser withCUInit(ICompilationUnitInitialiser cuInit);
	
//	/**
//	 * {@inheritDoc}
//	 * <br><br>
//	 * Creates a minimal {@link CompilationUnit} and puts obj in it.
//	 * @param obj: A given {@link ConcreteClassifier} instance
//	 */
//	@Override
//	public default CompilationUnit minimalInitialisationWithContainer(EObject obj) {
//		var castedO = (ConcreteClassifier) obj;
//		this.minimalInitialisation(castedO);
//		
//		var cuInit = this.getCUInit();
//		
//		if (cuInit == null) {
//			cuInit = this.getDefaultCUInit();
//		}
//		
//		CompilationUnit unit = cuInit.instantiate();
//		
//		cuInit.minimalInitialisation(unit);
//		cuInit.addClassifier(unit, castedO);
//		
//		return unit;
//	}
}
