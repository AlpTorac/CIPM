package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.containers.Module;
import org.emftext.language.java.containers.ContainersPackage;
import org.emftext.language.java.containers.Package;
import org.emftext.language.java.modules.ModuleDirective;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.containers.ModuleInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.modifiers.OpenInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesModuleDirectives;
import cipm.consistency.fitests.similarity.java.unittests.UsesNames;

public class ModuleTest extends EObjectSimilarityTest implements UsesModuleDirectives, UsesNames {
	protected Module initElement(Package[] pacs, ModuleDirective[] targets, boolean isOpen) {
		var initialiser = new ModuleInitialiser();
		Module result = initialiser.instantiate();

		Assertions.assertTrue(initialiser.setName(result, this.getDefaultName()));
		Assertions.assertTrue(initialiser.addPackages(result, pacs));
		Assertions.assertTrue(initialiser.addTargets(result, targets));

		if (isOpen) {
			Assertions.assertTrue(initialiser.setOpen(result, new OpenInitialiser().instantiate()));
		}

		return result;
	}

	@Test
	public void testOpen() {
		var objOne = this.initElement(null, null, true);
		var objTwo = this.initElement(null, null, false);

		this.testSimilarity(objOne, objTwo, ContainersPackage.Literals.MODULE__OPEN);
	}

	@Test
	public void testOpenNullCheck() {
		var objOne = this.initElement(null, null, true);

		this.testSimilarityNullCheck(objOne, new ModuleInitialiser(), false, ContainersPackage.Literals.MODULE__OPEN);
	}

	@Test
	public void testPackages() {
		var objOne = this.initElement(new Package[] { this.createMinimalPackage(new String[] { "ns1" }) }, null, false);
		var objTwo = this.initElement(new Package[] { this.createMinimalPackage(new String[] { "ns2" }) }, null, false);

		this.testSimilarity(objOne, objTwo, ContainersPackage.Literals.MODULE__PACKAGES);
	}

	@Test
	public void testPackagesSize() {
		var objOne = this.initElement(new Package[] { this.createMinimalPackage(new String[] { "ns1" }),
				this.createMinimalPackage(new String[] { "ns2" }) }, null, false);
		var objTwo = this.initElement(new Package[] { this.createMinimalPackage(new String[] { "ns1" }) }, null, false);

		this.testSimilarity(objOne, objTwo, ContainersPackage.Literals.MODULE__PACKAGES);
	}

	@Test
	public void testPackagesNullCheck() {
		var objOne = this.initElement(new Package[] { this.createMinimalPackage(new String[] { "ns1" }) }, null, false);

		this.testSimilarityNullCheck(objOne, new ModuleInitialiser(), false, ContainersPackage.Literals.MODULE__PACKAGES);
	}

	@Test
	public void testTargets() {
		var objOne = this.initElement(null, new ModuleDirective[] { this.createMinimalEMD(new String[] { "ns1" }) },
				false);
		var objTwo = this.initElement(null, new ModuleDirective[] { this.createMinimalOMD(new String[] { "ns1" }) },
				false);

		this.testSimilarity(objOne, objTwo, ContainersPackage.Literals.MODULE__TARGET);
	}

	@Test
	public void testTargetsSize() {
		var objOne = this.initElement(null, new ModuleDirective[] { this.createMinimalEMD(new String[] { "ns1" }),
				this.createMinimalEMD(new String[] { "ns2" }) }, false);
		var objTwo = this.initElement(null, new ModuleDirective[] { this.createMinimalEMD(new String[] { "ns1" }) },
				false);

		this.testSimilarity(objOne, objTwo, ContainersPackage.Literals.MODULE__TARGET);
	}

	@Test
	public void testTargetsNullCheck() {
		var objOne = this.initElement(null, new ModuleDirective[] { this.createMinimalEMD(new String[] { "ns1" }) },
				false);

		this.testSimilarityNullCheck(objOne, new ModuleInitialiser(), false, ContainersPackage.Literals.MODULE__TARGET);
	}
}
