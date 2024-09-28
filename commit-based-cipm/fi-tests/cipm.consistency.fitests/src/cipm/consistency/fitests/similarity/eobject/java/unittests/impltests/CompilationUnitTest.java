package cipm.consistency.fitests.similarity.eobject.java.unittests.impltests;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.containers.ContainersPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.eobject.java.AbstractEObjectJavaSimilarityTest;
import cipm.consistency.initialisers.eobject.java.containers.CompilationUnitInitialiser;
import cipm.consistency.fitests.similarity.eobject.java.unittests.UsesConcreteClassifiers;

public class CompilationUnitTest extends AbstractEObjectJavaSimilarityTest implements UsesConcreteClassifiers {
	protected CompilationUnit initElement(ConcreteClassifier[] classifiers) {
		var cuInit = new CompilationUnitInitialiser();
		var cu = cuInit.instantiate();
		Assertions.assertTrue(cuInit.setName(cu, this.getDefaultName()));
		Assertions.assertTrue(cuInit.addClassifiers(cu, classifiers));
		return cu;
	}

	@Test
	public void testClassifier() {
		var objOne = this.initElement(new ConcreteClassifier[] { this.createMinimalClass("cls1") });
		var objTwo = this.initElement(new ConcreteClassifier[] { this.createMinimalClass("cls2") });

		this.testSimilarity(objOne, objTwo, ContainersPackage.Literals.COMPILATION_UNIT__CLASSIFIERS);
	}

	@Test
	public void testClassifierSize() {
		var objOne = this.initElement(
				new ConcreteClassifier[] { this.createMinimalClass("cls1"), this.createMinimalClass("cls2") });
		var objTwo = this.initElement(new ConcreteClassifier[] { this.createMinimalClass("cls1") });

		this.testSimilarity(objOne, objTwo, ContainersPackage.Literals.COMPILATION_UNIT__CLASSIFIERS);
	}

	@Test
	public void testClassifierNullCheck() {
		this.testSimilarityNullCheck(this.initElement(new ConcreteClassifier[] { this.createMinimalClass("cls1") }),
				new CompilationUnitInitialiser(), false, ContainersPackage.Literals.COMPILATION_UNIT__CLASSIFIERS);
	}
}
