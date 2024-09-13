package cipm.consistency.fitests.similarity.java.eobject.unittests.interfacetests;

import org.emftext.language.java.containers.Package;

import org.emftext.language.java.modules.AccessProvidingModuleDirective;
import org.emftext.language.java.modules.ModuleReference;
import org.emftext.language.java.modules.ModulesPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.modules.IAccessProvidingModuleDirectiveInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.unittests.UsesModuleReferences;
import cipm.consistency.fitests.similarity.java.eobject.unittests.UsesPackages;

public class AccessProvidingModuleDirectiveTest extends EObjectSimilarityTest
		implements UsesModuleReferences, UsesPackages {
	protected AccessProvidingModuleDirective initElement(IAccessProvidingModuleDirectiveInitialiser init,
			ModuleReference[] modRefs, Package accessablePac) {
		var result = init.instantiate();
		Assertions.assertTrue(init.addModules(result, modRefs));
		Assertions.assertTrue(init.setAccessablePackage(result, accessablePac));
		return result;
	}

	@ParameterizedTest
	@ArgumentsSource(AccessProvidingModuleDirectiveTestParams.class)
	public void testModule(IAccessProvidingModuleDirectiveInitialiser init) {
		var objOne = this.initElement(init, new ModuleReference[] { this.createMinimalMR("mod1") }, null);
		var objTwo = this.initElement(init, new ModuleReference[] { this.createMinimalMR("mod2") }, null);

		this.testSimilarity(objOne, objTwo, ModulesPackage.Literals.ACCESS_PROVIDING_MODULE_DIRECTIVE__MODULES);
	}

	@ParameterizedTest
	@ArgumentsSource(AccessProvidingModuleDirectiveTestParams.class)
	public void testModuleSize(IAccessProvidingModuleDirectiveInitialiser init) {
		var objOne = this.initElement(init,
				new ModuleReference[] { this.createMinimalMR("mod1"), this.createMinimalMR("mod2") }, null);
		var objTwo = this.initElement(init, new ModuleReference[] { this.createMinimalMR("mod1") }, null);

		this.testSimilarity(objOne, objTwo, ModulesPackage.Literals.ACCESS_PROVIDING_MODULE_DIRECTIVE__MODULES);
	}

	@ParameterizedTest
	@ArgumentsSource(AccessProvidingModuleDirectiveTestParams.class)
	public void testModuleNullCheck(IAccessProvidingModuleDirectiveInitialiser init) {
		this.testSimilarityNullCheck(
				this.initElement(init, new ModuleReference[] { this.createMinimalMR("mod1") }, null), init, false,
				ModulesPackage.Literals.ACCESS_PROVIDING_MODULE_DIRECTIVE__MODULES);
	}

	@ParameterizedTest
	@ArgumentsSource(AccessProvidingModuleDirectiveTestParams.class)
	public void testAccessablePackage(IAccessProvidingModuleDirectiveInitialiser init) {
		var objOne = this.initElement(init, null, this.createMinimalPackage(new String[] { "ns1", "ns2" }));
		var objTwo = this.initElement(init, null, this.createMinimalPackage(new String[] { "ns3", "ns4" }));

		this.testSimilarity(objOne, objTwo,
				ModulesPackage.Literals.ACCESS_PROVIDING_MODULE_DIRECTIVE__ACCESSABLE_PACKAGE);
	}

	@ParameterizedTest
	@ArgumentsSource(AccessProvidingModuleDirectiveTestParams.class)
	public void testAccessablePackageNullCheck(IAccessProvidingModuleDirectiveInitialiser init) {
		this.testSimilarityNullCheck(
				this.initElement(init, null, this.createMinimalPackage(new String[] { "ns1", "ns2" })), init, false,
				ModulesPackage.Literals.ACCESS_PROVIDING_MODULE_DIRECTIVE__ACCESSABLE_PACKAGE);
	}
}
