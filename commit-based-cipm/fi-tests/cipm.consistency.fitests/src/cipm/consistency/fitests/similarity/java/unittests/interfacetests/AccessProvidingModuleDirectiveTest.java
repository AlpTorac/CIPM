package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.containers.Package;

import org.emftext.language.java.modules.AccessProvidingModuleDirective;
import org.emftext.language.java.modules.ModuleReference;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IAccessProvidingModuleDirectiveInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesModules;
import cipm.consistency.fitests.similarity.java.unittests.UsesPackages;

public class AccessProvidingModuleDirectiveTest extends EObjectSimilarityTest implements UsesModules, UsesPackages {
	protected AccessProvidingModuleDirective initElement(IAccessProvidingModuleDirectiveInitialiser init,
			ModuleReference[] modrefs, Package pac) {
		AccessProvidingModuleDirective result = init.instantiate();
		init.minimalInitialisation(result);
		init.addModules(result, modrefs);
		init.setAccessablePackage(result, pac);
		return result;
	}
	
	@ParameterizedTest
	@ArgumentsSource(AccessProvidingModuleDirectiveTestParams.class)
	public void testModuleReference(IAccessProvidingModuleDirectiveInitialiser init) {
		this.setResourceFileTestIdentifier("testModuleReference");
		
		var objOne = this.initElement(init, new ModuleReference[] {
				this.createMinimalMR("mod1")
		}, null);
		var objTwo = this.initElement(init, new ModuleReference[] {
				this.createMinimalMR("mod2")
		}, null);
		
		this.testX(objOne, objTwo, false);
	}
	
	@ParameterizedTest
	@ArgumentsSource(AccessProvidingModuleDirectiveTestParams.class)
	public void testAccessablePackage(IAccessProvidingModuleDirectiveInitialiser init) {
		this.setResourceFileTestIdentifier("testAccessablePackage");
		
		var objOne = this.initElement(init, null, this.createMinimalPackage(new String[] {"ns1", "ns2"}));
		var objTwo = this.initElement(init, null, this.createMinimalPackage(new String[] {"ns3", "ns4"}));
		
		this.testX(objOne, objTwo, false);
	}
}