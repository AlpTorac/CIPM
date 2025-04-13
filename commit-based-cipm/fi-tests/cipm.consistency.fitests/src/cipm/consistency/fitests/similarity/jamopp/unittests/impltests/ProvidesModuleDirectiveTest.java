package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.modules.ProvidesModuleDirective;
import org.emftext.language.java.modules.ModulesPackage;
import org.emftext.language.java.types.TypeReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesTypeReferences;
import cipm.consistency.initialisers.jamopp.modules.ProvidesModuleDirectiveInitialiser;

public class ProvidesModuleDirectiveTest extends AbstractJaMoPPSimilarityTest implements UsesTypeReferences {
	private TypeReference sp1;
	private TypeReference sp2;

	protected ProvidesModuleDirective initElement(TypeReference[] serviceProviders) {
		var pmdInit = new ProvidesModuleDirectiveInitialiser();
		var pmd = pmdInit.instantiate();
		Assertions.assertTrue(pmdInit.addServiceProviders(pmd, serviceProviders));
		return pmd;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		sp1 = this.createMinimalClsRef("cls1");
		sp2 = this.createMinimalClsRef("cls2");
		Assertions.assertFalse(this.isSimilar(sp1, sp2));
	}

	@Test
	public void testServiceProvider() {
		var objOne = this.initElement(new TypeReference[] { this.cloneEObjWithContainers(sp1) });
		var objTwo = this.initElement(new TypeReference[] { this.cloneEObjWithContainers(sp2) });

		this.testSimilarity(objOne, objTwo, ModulesPackage.Literals.PROVIDES_MODULE_DIRECTIVE__SERVICE_PROVIDERS);
	}

	@Test
	public void testServiceProviderSize() {
		var objOne = this.initElement(
				new TypeReference[] { this.cloneEObjWithContainers(sp1), this.cloneEObjWithContainers(sp2) });
		var objTwo = this.initElement(new TypeReference[] { this.cloneEObjWithContainers(sp1) });

		this.testSimilarity(objOne, objTwo, ModulesPackage.Literals.PROVIDES_MODULE_DIRECTIVE__SERVICE_PROVIDERS);
	}

	@Test
	public void testServiceProviderPosition() {
		var objOne = this.initElement(
				new TypeReference[] { this.cloneEObjWithContainers(sp1), this.cloneEObjWithContainers(sp2) });
		var objTwo = this.initElement(
				new TypeReference[] { this.cloneEObjWithContainers(sp2), this.cloneEObjWithContainers(sp1) });

		this.testSimilarity(objOne, objTwo, ModulesPackage.Literals.PROVIDES_MODULE_DIRECTIVE__SERVICE_PROVIDERS);
	}

	@Test
	public void testServiceProviderDuplication() {
		var objOne = this.initElement(
				new TypeReference[] { this.cloneEObjWithContainers(sp1), this.cloneEObjWithContainers(sp1) });
		var objTwo = this.initElement(new TypeReference[] { this.cloneEObjWithContainers(sp1) });

		this.testSimilarity(objOne, objTwo, ModulesPackage.Literals.PROVIDES_MODULE_DIRECTIVE__SERVICE_PROVIDERS);
	}

	@Test
	public void testServiceProviderNullCheck() {
		this.testSimilarityNullCheck(this.initElement(new TypeReference[] { this.cloneEObjWithContainers(sp1) }),
				new ProvidesModuleDirectiveInitialiser(), false,
				ModulesPackage.Literals.PROVIDES_MODULE_DIRECTIVE__SERVICE_PROVIDERS);
	}
}
