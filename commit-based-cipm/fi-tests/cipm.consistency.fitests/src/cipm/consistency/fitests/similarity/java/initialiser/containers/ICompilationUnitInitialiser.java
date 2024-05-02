package cipm.consistency.fitests.similarity.java.initialiser.containers;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.CompilationUnit;

import cipm.consistency.fitests.similarity.java.initialiser.IJavaRootInitialiser;

public interface ICompilationUnitInitialiser extends IJavaRootInitialiser {
	public default void addImport(CompilationUnit cu, String imprt) {
		if (imprt != null) {
			cu.addImport(imprt);
			// TODO: Write assertions
		}
	}
	
	public default void addPackageImport(CompilationUnit cu, String imprt) {
		if (imprt != null) {
			cu.addPackageImport(imprt);
			// TODO: Write assertions
		}
	}
	
	public default void addClassifier(CompilationUnit cu, ConcreteClassifier cc) {
		if (cc != null) {
			cu.getClassifiers().add(cc);
			assert cu.getClassifiers().contains(cc);
			assert cu.getContainedClassifier(cc.getQualifiedName()).equals(cc);
		}
	}
	
	public default void addClassifierInSamePackage(CompilationUnit cu, ConcreteClassifier cc) {
		if (cc != null) {
			cu.getClassifiersInSamePackage().add(cc);
			assert cu.getClassifiersInSamePackage().contains(cc);
			assert cu.getContainedClassifier(cc.getQualifiedName()).equals(cc);
		}
	}
}
