package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.containers.ContainersPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesConcreteClassifiers;
import cipm.consistency.initialisers.jamopp.containers.CompilationUnitInitialiser;

public class CompilationUnitTest extends AbstractJaMoPPSimilarityTest implements UsesConcreteClassifiers {
	private ConcreteClassifier clsf1;
	private ConcreteClassifier clsf2;

	protected CompilationUnit initElement(ConcreteClassifier[] classifiers) {
		var cuInit = new CompilationUnitInitialiser();
		var cu = cuInit.instantiate();
		Assertions.assertTrue(cuInit.setName(cu, this.getDefaultName()));
		Assertions.assertTrue(cuInit.addClassifiers(cu, classifiers));
		return cu;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		clsf1 = this.createMinimalClass("cls1");
		clsf2 = this.createMinimalClass("cls2");
		Assertions.assertFalse(this.isSimilar(clsf1, clsf2));
	}

	@Test
	public void testClassifier() {
		var objOne = this.initElement(new ConcreteClassifier[] { this.cloneEObjWithContainers(clsf1) });
		var objTwo = this.initElement(new ConcreteClassifier[] { this.cloneEObjWithContainers(clsf2) });

		this.testSimilarity(objOne, objTwo, ContainersPackage.Literals.COMPILATION_UNIT__CLASSIFIERS);
	}

	@Test
	public void testClassifierSize() {
		var objOne = this.initElement(
				new ConcreteClassifier[] { this.cloneEObjWithContainers(clsf1), this.cloneEObjWithContainers(clsf2) });
		var objTwo = this.initElement(new ConcreteClassifier[] { this.cloneEObjWithContainers(clsf1) });

		this.testSimilarity(objOne, objTwo, ContainersPackage.Literals.COMPILATION_UNIT__CLASSIFIERS);
	}

	@Test
	public void testClassifierPosition() {
		var objOne = this.initElement(
				new ConcreteClassifier[] { this.cloneEObjWithContainers(clsf1), this.cloneEObjWithContainers(clsf2) });
		var objTwo = this.initElement(
				new ConcreteClassifier[] { this.cloneEObjWithContainers(clsf2), this.cloneEObjWithContainers(clsf1) });

		this.testSimilarity(objOne, objTwo, ContainersPackage.Literals.COMPILATION_UNIT__CLASSIFIERS);
	}

	@Test
	public void testClassifierDuplication() {
		var objOne = this.initElement(
				new ConcreteClassifier[] { this.cloneEObjWithContainers(clsf1), this.cloneEObjWithContainers(clsf1) });
		var objTwo = this.initElement(new ConcreteClassifier[] { this.cloneEObjWithContainers(clsf1) });

		this.testSimilarity(objOne, objTwo, ContainersPackage.Literals.COMPILATION_UNIT__CLASSIFIERS);
	}

	@Test
	public void testClassifierNullCheck() {
		this.testSimilarityNullCheck(this.initElement(new ConcreteClassifier[] { this.cloneEObjWithContainers(clsf1) }),
				new CompilationUnitInitialiser(), false, ContainersPackage.Literals.COMPILATION_UNIT__CLASSIFIERS);
	}
}
