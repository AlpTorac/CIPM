package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.CompilationUnit;

public interface ICompilationUnitInitialiser extends IJavaRootInitialiser {
	@Override
	public CompilationUnit instantiate();
	
	public default void addImport(CompilationUnit cu, String imprt) {
		if (imprt != null) {
			cu.addImport(imprt);
		}
	}
	
	public default void addPackageImport(CompilationUnit cu, String imprt) {
		if (imprt != null) {
			cu.addPackageImport(imprt);
		}
	}
	
	public default void addClassifier(CompilationUnit cu, ConcreteClassifier cc) {
		if (cc != null) {
			cu.getClassifiers().add(cc);
			assert cu.getClassifiers().contains(cc);
		}
	}
	
	public default void addClassifierInSamePackage(CompilationUnit cu, ConcreteClassifier cc) {
		if (cc != null) {
			cu.getClassifiersInSamePackage().add(cc);
			assert cu.getClassifiersInSamePackage().contains(cc);
		}
	}
}
