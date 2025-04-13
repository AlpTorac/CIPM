package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.containers.ContainersPackage;
import org.emftext.language.java.containers.Module;
import org.emftext.language.java.containers.Package;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesConcreteClassifiers;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesModules;
import cipm.consistency.initialisers.jamopp.containers.PackageInitialiser;

public class PackageTest extends AbstractJaMoPPSimilarityTest implements UsesModules, UsesConcreteClassifiers {
	private Module mod1;
	private Module mod2;
	private ConcreteClassifier cls1;
	private ConcreteClassifier cls2;

	protected Package initElement(Module mod, ConcreteClassifier[] clss) {
		var initialiser = new PackageInitialiser();
		Package pac = initialiser.instantiate();
		Assertions.assertTrue(initialiser.setModule(pac, mod));
		Assertions.assertTrue(initialiser.addClassifiers(pac, clss));

		return pac;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		mod1 = this.createMinimalModule("mod1");
		mod2 = this.createMinimalModule("mod2");
		Assertions.assertFalse(this.isSimilar(mod1, mod2));

		cls1 = this.createMinimalClass("cls1");
		cls2 = this.createMinimalClass("cls2");
		Assertions.assertFalse(this.isSimilar(cls1, cls2));
	}

	@Test
	public void testModule() {
		var objOne = this.initElement(this.cloneEObjWithContainers(mod1), null);
		var objTwo = this.initElement(this.cloneEObjWithContainers(mod2), null);

		this.testSimilarity(objOne, objTwo, ContainersPackage.Literals.PACKAGE__MODULE);
	}

	@Test
	public void testModuleNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.cloneEObjWithContainers(mod1), null),
				new PackageInitialiser(), false, ContainersPackage.Literals.PACKAGE__MODULE);
	}

	@Test
	public void testClassifiers() {
		var objOne = this.initElement(null, new ConcreteClassifier[] { this.cloneEObjWithContainers(cls1) });
		var objTwo = this.initElement(null, new ConcreteClassifier[] { this.cloneEObjWithContainers(cls2) });

		this.testSimilarity(objOne, objTwo, ContainersPackage.Literals.PACKAGE__CLASSIFIERS);
	}

	@Test
	public void testClassifiersSize() {
		var objOne = this.initElement(null,
				new ConcreteClassifier[] { this.cloneEObjWithContainers(cls1), this.cloneEObjWithContainers(cls2) });
		var objTwo = this.initElement(null, new ConcreteClassifier[] { this.cloneEObjWithContainers(cls1) });

		this.testSimilarity(objOne, objTwo, ContainersPackage.Literals.PACKAGE__CLASSIFIERS);
	}

	@Test
	public void testClassifiersPosition() {
		var objOne = this.initElement(null,
				new ConcreteClassifier[] { this.cloneEObjWithContainers(cls1), this.cloneEObjWithContainers(cls2) });
		var objTwo = this.initElement(null,
				new ConcreteClassifier[] { this.cloneEObjWithContainers(cls2), this.cloneEObjWithContainers(cls1) });

		this.testSimilarity(objOne, objTwo, ContainersPackage.Literals.PACKAGE__CLASSIFIERS);
	}

	@Test
	public void testClassifiersDuplication() {
		var objOne = this.initElement(null,
				new ConcreteClassifier[] { this.cloneEObjWithContainers(cls1), this.cloneEObjWithContainers(cls1) });
		var objTwo = this.initElement(null, new ConcreteClassifier[] { this.cloneEObjWithContainers(cls1) });

		this.testSimilarity(objOne, objTwo, ContainersPackage.Literals.PACKAGE__CLASSIFIERS);
	}

	@Test
	public void testClassifiersNullCheck() {
		this.testSimilarityNullCheck(
				this.initElement(null, new ConcreteClassifier[] { this.cloneEObjWithContainers(cls1) }),
				new PackageInitialiser(), false, ContainersPackage.Literals.PACKAGE__CLASSIFIERS);
	}
}
