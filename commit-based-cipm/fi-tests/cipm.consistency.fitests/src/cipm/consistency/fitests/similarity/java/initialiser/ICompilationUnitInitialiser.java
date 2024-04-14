package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.containers.CompilationUnit;

public interface ICompilationUnitInitialiser extends IJavaRootInitialiser {
	@Override
	public CompilationUnit getCurrentObject();
	
	public default void addImport(String imprt) {
		var cObj = this.getCurrentObject();
		
		if (imprt != null) {
			cObj.addImport(imprt);
		}
	}
	
	public default void addPackageImport(String imprt) {
		var cObj = this.getCurrentObject();
		
		if (imprt != null) {
			cObj.addPackageImport(imprt);
		}
	}
	
	public default void addClassifier(ConcreteClassifier cc) {
		var cObj = this.getCurrentObject();
		
		if (cc != null) {
			cObj.getClassifiers().add(cc);
			assert cObj.getClassifiers().contains(cc);
		}
	}
	
	public default void addClassifierInSamePackage(ConcreteClassifier cc) {
		var cObj = this.getCurrentObject();
		
		if (cc != null) {
			cObj.getClassifiersInSamePackage().add(cc);
			assert cObj.getClassifiersInSamePackage().contains(cc);
		}
	}
	
	@Override
	public default CompilationUnit build() {
		return (CompilationUnit) IJavaRootInitialiser.super.build();
	}
}
