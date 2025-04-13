package cipm.consistency.fitests.similarity.jamopp.unittests.interfacetests;

import java.util.stream.Stream;

import org.emftext.language.java.containers.Package;

import org.emftext.language.java.modules.AccessProvidingModuleDirective;
import org.emftext.language.java.modules.ModuleReference;
import org.emftext.language.java.modules.ModulesPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesModuleReferences;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesPackages;
import cipm.consistency.initialisers.jamopp.modules.IAccessProvidingModuleDirectiveInitialiser;

public class AccessProvidingModuleDirectiveTest extends AbstractJaMoPPSimilarityTest
		implements UsesModuleReferences, UsesPackages {
	private ModuleReference modRef1;
	private ModuleReference modRef2;
	private Package aPac1;
	private Package aPac2;

	private static Stream<Arguments> provideArguments() {
		return AbstractJaMoPPSimilarityTest
				.getAllInitialiserArgumentsFor(IAccessProvidingModuleDirectiveInitialiser.class);
	}

	protected AccessProvidingModuleDirective initElement(IAccessProvidingModuleDirectiveInitialiser init,
			ModuleReference[] modRefs, Package accessablePac) {
		var result = init.instantiate();
		Assertions.assertTrue(init.initialise(result));
		Assertions.assertTrue(init.addModules(result, modRefs));
		Assertions.assertTrue(init.setAccessablePackage(result, accessablePac));
		return result;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		modRef1 = this.createMinimalMR("mod1", new String[] { "ns1" });
		modRef2 = this.createMinimalMR("mod2", new String[] { "ns2" });
		Assertions.assertFalse(this.isSimilar(modRef1, modRef2));

		aPac1 = this.createMinimalPackage(new String[] { "ns1", "ns2" });
		aPac2 = this.createMinimalPackage(new String[] { "ns3", "ns4" });
		Assertions.assertFalse(this.isSimilar(aPac1, aPac2));
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testModule(IAccessProvidingModuleDirectiveInitialiser init, String displayName) {
		var objOne = this.initElement(init, new ModuleReference[] { this.cloneEObjWithContainers(modRef1) }, null);
		var objTwo = this.initElement(init, new ModuleReference[] { this.cloneEObjWithContainers(modRef2) }, null);

		this.testSimilarity(objOne, objTwo, ModulesPackage.Literals.ACCESS_PROVIDING_MODULE_DIRECTIVE__MODULES);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testModuleSize(IAccessProvidingModuleDirectiveInitialiser init, String displayName) {
		var objOne = this.initElement(init,
				new ModuleReference[] { this.cloneEObjWithContainers(modRef1), this.cloneEObjWithContainers(modRef2) },
				null);
		var objTwo = this.initElement(init, new ModuleReference[] { this.cloneEObjWithContainers(modRef1) }, null);

		this.testSimilarity(objOne, objTwo, ModulesPackage.Literals.ACCESS_PROVIDING_MODULE_DIRECTIVE__MODULES);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testModulePosition(IAccessProvidingModuleDirectiveInitialiser init, String displayName) {
		var objOne = this.initElement(init,
				new ModuleReference[] { this.cloneEObjWithContainers(modRef1), this.cloneEObjWithContainers(modRef2) },
				null);
		var objTwo = this.initElement(init,
				new ModuleReference[] { this.cloneEObjWithContainers(modRef2), this.cloneEObjWithContainers(modRef1) },
				null);

		this.testSimilarity(objOne, objTwo, ModulesPackage.Literals.ACCESS_PROVIDING_MODULE_DIRECTIVE__MODULES);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testModuleDuplication(IAccessProvidingModuleDirectiveInitialiser init, String displayName) {
		var objOne = this.initElement(init,
				new ModuleReference[] { this.cloneEObjWithContainers(modRef1), this.cloneEObjWithContainers(modRef1) },
				null);
		var objTwo = this.initElement(init, new ModuleReference[] { this.cloneEObjWithContainers(modRef1) }, null);

		this.testSimilarity(objOne, objTwo, ModulesPackage.Literals.ACCESS_PROVIDING_MODULE_DIRECTIVE__MODULES);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testModuleNullCheck(IAccessProvidingModuleDirectiveInitialiser init, String displayName) {
		this.testSimilarityNullCheck(
				this.initElement(init, new ModuleReference[] { this.cloneEObjWithContainers(modRef1) }, null), init,
				true, ModulesPackage.Literals.ACCESS_PROVIDING_MODULE_DIRECTIVE__MODULES);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testAccessablePackage(IAccessProvidingModuleDirectiveInitialiser init, String displayName) {
		var objOne = this.initElement(init, null, this.cloneEObjWithContainers(aPac1));
		var objTwo = this.initElement(init, null, this.cloneEObjWithContainers(aPac2));

		this.testSimilarity(objOne, objTwo,
				ModulesPackage.Literals.ACCESS_PROVIDING_MODULE_DIRECTIVE__ACCESSABLE_PACKAGE);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testAccessablePackageNullCheck(IAccessProvidingModuleDirectiveInitialiser init, String displayName) {
		this.testSimilarityNullCheck(this.initElement(init, null, this.cloneEObjWithContainers(aPac1)), init, true,
				ModulesPackage.Literals.ACCESS_PROVIDING_MODULE_DIRECTIVE__ACCESSABLE_PACKAGE);
	}
}
