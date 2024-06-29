package cipm.consistency.fitests.similarity.java.initialiser.containers;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.CompilationUnit;

public interface ICompilationUnitInitialiser extends IJavaRootInitialiser {
	public default boolean addClassifier(CompilationUnit cu, ConcreteClassifier cc) {
		if (cc != null) {
			cu.getClassifiers().add(cc);
			return cu.getClassifiers().contains(cc) &&
					cu.getContainedClassifier(cc.getQualifiedName()).equals(cc);
		}
		return false;
	}
	
	public default boolean addClassifiers(CompilationUnit cu, ConcreteClassifier[] ccs) {
		return this.addXs(cu, ccs, this::addClassifier);
	}
}
