package cipm.consistency.fitests.similarity.java.initialiser.containers;

import java.util.List;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.CompilationUnit;

public interface ICompilationUnitInitialiser extends IJavaRootInitialiser {
	public default void addClassifier(CompilationUnit cu, ConcreteClassifier cc) {
		if (cc != null) {
			cu.getClassifiers().add(cc);
			assert cu.getClassifiers().contains(cc);
			assert cu.getContainedClassifier(cc.getQualifiedName()).equals(cc);
		}
	}
	
	public default void addClassifiers(CompilationUnit cu, ConcreteClassifier[] ccs) {
		this.addXs(cu, ccs, this::addClassifier);
		
		assert cu.getClassifiers().containsAll(List.of(ccs));
	}
}
