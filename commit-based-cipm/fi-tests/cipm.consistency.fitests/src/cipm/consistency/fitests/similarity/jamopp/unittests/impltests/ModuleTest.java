package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.containers.Module;

import java.util.function.Supplier;

import org.emftext.language.java.containers.ContainersPackage;
import org.emftext.language.java.containers.Package;
import org.emftext.language.java.modifiers.Open;
import org.emftext.language.java.modules.ModuleDirective;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;

public class ModuleTest extends AbstractJaMoPPSimilarityTest {
	private final Supplier<Package> packages1 = () -> getAPI().newPackage().withAddedNamespaces("ns1").createNow();
	private final Supplier<Package> packages2 = () -> getAPI().newPackage().withAddedNamespaces("ns2").createNow();

	private final Supplier<ModuleDirective> target1 = () -> getAPI().newExportsModuleDirective().createNow();
	private final Supplier<ModuleDirective> target2 = () -> getAPI().newOpensModuleDirective().createNow();

	private final Supplier<Open> open = () -> getAPI().newOpen();

	@Test
	public void testOpen() {
		this.testSimilarity(getAPI().newModule().withOpen(open.get()).createNow(), getAPI().newModule().createNow(),
				ContainersPackage.Literals.MODULE__OPEN);
	}

	@Test
	public void testOpenNullCheck() {
		this.testSimilarityNullCheck(this.initElement(null, null, true), new ModuleInitialiser(), false,
				ContainersPackage.Literals.MODULE__OPEN);
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
		this.testSimilarityNullCheck(
				this.initElement(new Package[] { this.createMinimalPackage(new String[] { "ns1" }) }, null, false),
				new ModuleInitialiser(), false, ContainersPackage.Literals.MODULE__PACKAGES);
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
		this.testSimilarityNullCheck(
				this.initElement(null, new ModuleDirective[] { this.createMinimalEMD(new String[] { "ns1" }) }, false),
				new ModuleInitialiser(), false, ContainersPackage.Literals.MODULE__TARGET);
	}
}
