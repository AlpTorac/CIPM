package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.containers.Module;
import org.emftext.language.java.containers.ContainersPackage;
import org.emftext.language.java.containers.Package;
import org.emftext.language.java.modifiers.Open;
import org.emftext.language.java.modifiers.impl.OpenImpl;
import org.emftext.language.java.modules.ModuleDirective;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesModuleDirectives;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesModules;
import cipm.consistency.initialisers.jamopp.containers.ModuleInitialiser;

public class ModuleTest extends AbstractJaMoPPSimilarityTest implements UsesModuleDirectives, UsesModules {
	private Package pac1;
	private Package pac2;
	private ModuleDirective target1;
	private ModuleDirective target2;
	private Open open1;
	private Open open2;

	protected Module initElement(Package[] pacs, ModuleDirective[] targets, Open open) {
		var initialiser = new ModuleInitialiser();
		Module result = initialiser.instantiate();

		Assertions.assertTrue(initialiser.addPackages(result, pacs));
		Assertions.assertTrue(initialiser.addTargets(result, targets));
		Assertions.assertTrue(initialiser.setOpen(result, open));

		return result;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		pac1 = this.createMinimalPackage(new String[] { "ns1" });
		pac2 = this.createMinimalPackage(new String[] { "ns2" });
		Assertions.assertFalse(this.isSimilar(pac1, pac2));

		target1 = this.createMinimalEMD(new String[] { "ns1" });
		target2 = this.createMinimalOMD(new String[] { "ns2" });
		Assertions.assertFalse(this.isSimilar(target1, target2));

		open1 = this.createOpen();
		/*
		 * Since there is currently no way to make Open instances different, use an
		 * anonymous class instance to force difference.
		 */
		open2 = new OpenImpl() {
		};
		Assertions.assertFalse(this.isSimilar(open1, open2));
	}

	@Test
	public void testOpen() {
		var objOne = this.initElement(null, null, this.cloneEObjWithContainers(open1));
		var objTwo = this.initElement(null, null, this.cloneEObjWithContainers(open2));

		this.testSimilarity(objOne, objTwo, ContainersPackage.Literals.MODULE__OPEN);
	}

	@Test
	public void testOpenNullCheck() {
		this.testSimilarityNullCheck(this.initElement(null, null, this.cloneEObjWithContainers(open1)),
				new ModuleInitialiser(), false, ContainersPackage.Literals.MODULE__OPEN);
	}

	@Test
	public void testPackages() {
		var objOne = this.initElement(new Package[] { this.cloneEObjWithContainers(pac1) }, null, null);
		var objTwo = this.initElement(new Package[] { this.cloneEObjWithContainers(pac2) }, null, null);

		this.testSimilarity(objOne, objTwo, ContainersPackage.Literals.MODULE__PACKAGES);
	}

	@Test
	public void testPackagesSize() {
		var objOne = this.initElement(
				new Package[] { this.cloneEObjWithContainers(pac1), this.cloneEObjWithContainers(pac2) }, null, null);
		var objTwo = this.initElement(new Package[] { this.cloneEObjWithContainers(pac1) }, null, null);

		this.testSimilarity(objOne, objTwo, ContainersPackage.Literals.MODULE__PACKAGES);
	}

	@Test
	public void testPackagesPosition() {
		var objOne = this.initElement(
				new Package[] { this.cloneEObjWithContainers(pac1), this.cloneEObjWithContainers(pac2) }, null, null);
		var objTwo = this.initElement(
				new Package[] { this.cloneEObjWithContainers(pac2), this.cloneEObjWithContainers(pac1) }, null, null);

		this.testSimilarity(objOne, objTwo, ContainersPackage.Literals.MODULE__PACKAGES);
	}

	@Test
	public void testPackagesDuplication() {
		var objOne = this.initElement(
				new Package[] { this.cloneEObjWithContainers(pac1), this.cloneEObjWithContainers(pac1) }, null, null);
		var objTwo = this.initElement(new Package[] { this.cloneEObjWithContainers(pac1) }, null, null);

		this.testSimilarity(objOne, objTwo, ContainersPackage.Literals.MODULE__PACKAGES);
	}

	@Test
	public void testPackagesNullCheck() {
		this.testSimilarityNullCheck(this.initElement(new Package[] { this.cloneEObjWithContainers(pac1) }, null, null),
				new ModuleInitialiser(), false, ContainersPackage.Literals.MODULE__PACKAGES);
	}

	@Test
	public void testTargets() {
		var objOne = this.initElement(null, new ModuleDirective[] { this.cloneEObjWithContainers(target1) }, null);
		var objTwo = this.initElement(null, new ModuleDirective[] { this.cloneEObjWithContainers(target2) }, null);

		this.testSimilarity(objOne, objTwo, ContainersPackage.Literals.MODULE__TARGET);
	}

	@Test
	public void testTargetsSize() {
		var objOne = this.initElement(null,
				new ModuleDirective[] { this.cloneEObjWithContainers(target1), this.cloneEObjWithContainers(target2) },
				null);
		var objTwo = this.initElement(null, new ModuleDirective[] { this.cloneEObjWithContainers(target1) }, null);

		this.testSimilarity(objOne, objTwo, ContainersPackage.Literals.MODULE__TARGET);
	}

	@Test
	public void testTargetsPosition() {
		var objOne = this.initElement(null,
				new ModuleDirective[] { this.cloneEObjWithContainers(target1), this.cloneEObjWithContainers(target2) },
				null);
		var objTwo = this.initElement(null,
				new ModuleDirective[] { this.cloneEObjWithContainers(target2), this.cloneEObjWithContainers(target1) },
				null);

		this.testSimilarity(objOne, objTwo, ContainersPackage.Literals.MODULE__TARGET);
	}

	@Test
	public void testTargetsDuplication() {
		var objOne = this.initElement(null,
				new ModuleDirective[] { this.cloneEObjWithContainers(target1), this.cloneEObjWithContainers(target1) },
				null);
		var objTwo = this.initElement(null, new ModuleDirective[] { this.cloneEObjWithContainers(target1) }, null);

		this.testSimilarity(objOne, objTwo, ContainersPackage.Literals.MODULE__TARGET);
	}

	@Test
	public void testTargetsNullCheck() {
		this.testSimilarityNullCheck(
				this.initElement(null, new ModuleDirective[] { this.cloneEObjWithContainers(target1) }, null),
				new ModuleInitialiser(), false, ContainersPackage.Literals.MODULE__TARGET);
	}
}
