package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.modules.ProvidesModuleDirective;
import org.emftext.language.java.modules.ModulesPackage;
import org.emftext.language.java.types.TypeReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.modules.ProvidesModuleDirectiveInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesTypeReferences;

public class ProvidesModuleDirectiveTest extends EObjectSimilarityTest implements UsesTypeReferences {
	protected ProvidesModuleDirective initElement(TypeReference[] serviceProviders) {
		var pmdInit = new ProvidesModuleDirectiveInitialiser();
		var pmd = pmdInit.instantiate();
		Assertions.assertTrue(pmdInit.addServiceProviders(pmd, serviceProviders));
		return pmd;
	}

	@Test
	public void testServiceProvider() {
		this.setResourceFileTestIdentifier("testServiceProvider");

		var objOne = this.initElement(new TypeReference[] { this.createMinimalClsRef("cls1") });
		var objTwo = this.initElement(new TypeReference[] { this.createMinimalClsRef("cls2") });

		this.testSimilarity(objOne, objTwo, ModulesPackage.Literals.PROVIDES_MODULE_DIRECTIVE__SERVICE_PROVIDERS);
	}

	@Test
	public void testServiceProviderNullCheck() {
		this.setResourceFileTestIdentifier("testServiceProviderNullCheck");

		var objOne = this.initElement(new TypeReference[] { this.createMinimalClsRef("cls1") });
		var objTwo = new ProvidesModuleDirectiveInitialiser().instantiate();

		this.testSimilarity(objOne, objTwo, ModulesPackage.Literals.PROVIDES_MODULE_DIRECTIVE__SERVICE_PROVIDERS);
	}
}
