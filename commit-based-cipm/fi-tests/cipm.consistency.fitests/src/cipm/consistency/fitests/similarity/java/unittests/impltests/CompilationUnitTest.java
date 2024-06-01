package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.CompilationUnit;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.containers.CompilationUnitInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesConcreteClassifiers;

public class CompilationUnitTest extends EObjectSimilarityTest implements UsesConcreteClassifiers {
	protected CompilationUnit initElement(ConcreteClassifier[] ccs) {
		var cuInit = new CompilationUnitInitialiser();
		var cu = cuInit.instantiate();
		cuInit.minimalInitialisation(cu);
		cuInit.addClassifiers(cu, ccs);
		return cu;
	}
	
	@Test
	public void testClassifier() {
		this.setResourceFileTestIdentifier("testClassifier");
		
		var objOne = this.initElement(new ConcreteClassifier[] {this.createMinimalClass("cls1")});
		var objTwo = this.initElement(new ConcreteClassifier[] {this.createMinimalClass("cls2")});
		
		this.compareX(objOne, objTwo, false);
	}
}
