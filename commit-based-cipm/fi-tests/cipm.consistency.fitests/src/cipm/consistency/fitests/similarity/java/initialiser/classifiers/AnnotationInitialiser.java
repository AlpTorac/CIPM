package cipm.consistency.fitests.similarity.java.initialiser.classifiers;

import org.emftext.language.java.classifiers.Annotation;
import org.emftext.language.java.classifiers.ClassifiersFactory;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.ICompilationUnitInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.helper.ICompilationUnitContaineeInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IMemberContainerInitialiser;

public class AnnotationInitialiser implements IAnnotationInitialiser, ICompilationUnitContaineeInitialiser {
	private ICompilationUnitInitialiser cuInit;
	
	@Override
	public ICompilationUnitInitialiser getCUInit() {
		return this.cuInit;
	}
	
	@Override
	public AnnotationInitialiser withCUInit(ICompilationUnitInitialiser cuInit) {
		this.cuInit = cuInit;
		return this;
	}

	@Override
	public Annotation instantiate() {
		var fac = ClassifiersFactory.eINSTANCE;
		return fac.createAnnotation();
	}

	@Override
	public AnnotationInitialiser newInitialiser() {
		return new AnnotationInitialiser();
	}

}
