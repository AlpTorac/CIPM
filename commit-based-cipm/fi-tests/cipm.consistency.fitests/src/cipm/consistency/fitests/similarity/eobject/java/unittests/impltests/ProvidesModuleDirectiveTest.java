package cipm.consistency.fitests.similarity.eobject.java.unittests.impltests;

import org.emftext.language.java.modules.ProvidesModuleDirective;
import org.emftext.language.java.modules.ModulesPackage;
import org.emftext.language.java.types.TypeReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.eobject.java.AbstractEObjectJavaSimilarityTest;
import cipm.consistency.initialisers.eobject.java.modules.ProvidesModuleDirectiveInitialiser;
import cipm.consistency.fitests.similarity.eobject.java.unittests.UsesTypeReferences;

public class ProvidesModuleDirectiveTest extends AbstractEObjectJavaSimilarityTest implements UsesTypeReferences {
	protected ProvidesModuleDirective initElement(TypeReference[] serviceProviders) {
		var pmdInit = new ProvidesModuleDirectiveInitialiser();
		var pmd = pmdInit.instantiate();
		Assertions.assertTrue(pmdInit.addServiceProviders(pmd, serviceProviders));
		return pmd;
	}

	@Test
	public void testServiceProvider() {
		var objOne = this.initElement(new TypeReference[] { this.createMinimalClsRef("cls1") });
		var objTwo = this.initElement(new TypeReference[] { this.createMinimalClsRef("cls2") });

		this.testSimilarity(objOne, objTwo, ModulesPackage.Literals.PROVIDES_MODULE_DIRECTIVE__SERVICE_PROVIDERS);
	}

	@Test
	public void testServiceProviderSize() {
		var objOne = this.initElement(
				new TypeReference[] { this.createMinimalClsRef("cls1"), this.createMinimalClsRef("cls2") });
		var objTwo = this.initElement(new TypeReference[] { this.createMinimalClsRef("cls1") });

		this.testSimilarity(objOne, objTwo, ModulesPackage.Literals.PROVIDES_MODULE_DIRECTIVE__SERVICE_PROVIDERS);
	}

	@Test
	public void testServiceProviderNullCheck() {
		this.testSimilarityNullCheck(this.initElement(new TypeReference[] { this.createMinimalClsRef("cls1") }),
				new ProvidesModuleDirectiveInitialiser(), false,
				ModulesPackage.Literals.PROVIDES_MODULE_DIRECTIVE__SERVICE_PROVIDERS);
	}
}
