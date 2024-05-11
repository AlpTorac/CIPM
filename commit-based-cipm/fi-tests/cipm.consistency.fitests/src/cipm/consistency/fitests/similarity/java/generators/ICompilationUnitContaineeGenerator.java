package cipm.consistency.fitests.similarity.java.generators;

import org.emftext.language.java.classifiers.ConcreteClassifier;

import cipm.consistency.fitests.similarity.java.initialiser.containers.CompilationUnitInitialiser;

public interface ICompilationUnitContaineeGenerator {
	public default void setDefaultCUGen() {
		this.setCUGen(new CompilationUnitGenerator());
	}
	public void setCUGen(CompilationUnitGenerator cuGen);
	public CompilationUnitGenerator getCUGen();
	
	public default void setCU(ConcreteClassifier cc) {
		var gen = this.getCUGen();
		if (gen != null)
			new CompilationUnitInitialiser().addClassifier(
					this.getCUGen().generateElement(), cc);
	}
}
