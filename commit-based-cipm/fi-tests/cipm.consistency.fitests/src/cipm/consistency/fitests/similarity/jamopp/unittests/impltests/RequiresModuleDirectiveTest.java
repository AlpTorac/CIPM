package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.modifiers.ModuleRequiresModifier;
import org.emftext.language.java.modules.ModuleReference;
import org.emftext.language.java.modules.ModulesPackage;
import org.emftext.language.java.modules.RequiresModuleDirective;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesLiterals;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesModuleReferences;
import cipm.consistency.initialisers.jamopp.modules.RequiresModuleDirectiveInitialiser;

public class RequiresModuleDirectiveTest extends AbstractJaMoPPSimilarityTest
		implements UsesLiterals, UsesModuleReferences {
	private ModuleRequiresModifier modif1;
	private ModuleRequiresModifier modif2;
	private ModuleReference reqMod1;
	private ModuleReference reqMod2;

	protected RequiresModuleDirective initElement(ModuleRequiresModifier modif, ModuleReference reqMod) {
		var rmdInit = new RequiresModuleDirectiveInitialiser();
		var rmd = rmdInit.instantiate();
		Assertions.assertTrue(rmdInit.setModifier(rmd, modif));
		Assertions.assertTrue(rmdInit.setRequiredModule(rmd, reqMod));
		return rmd;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		modif1 = this.createStatic();
		modif2 = this.createTransitive();
		Assertions.assertFalse(this.isSimilar(modif1, modif2));

		reqMod1 = this.createMinimalMR("mod1", new String[] { "ns1", "ns2" });
		reqMod2 = this.createMinimalMR("mod2", new String[] { "ns3", "ns4" });
		Assertions.assertFalse(this.isSimilar(reqMod1, reqMod2));
	}

	@Test
	public void testModifier() {
		var objOne = this.initElement(this.cloneEObjWithContainers(modif1), null);
		var objTwo = this.initElement(this.cloneEObjWithContainers(modif2), null);

		this.testSimilarity(objOne, objTwo, ModulesPackage.Literals.REQUIRES_MODULE_DIRECTIVE__MODIFIER);
	}

	@Test
	public void testModifierNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.cloneEObjWithContainers(modif1), null),
				new RequiresModuleDirectiveInitialiser(), false,
				ModulesPackage.Literals.REQUIRES_MODULE_DIRECTIVE__MODIFIER);
	}

	@Test
	public void testRequiredModule() {
		var objOne = this.initElement(null, this.cloneEObjWithContainers(reqMod1));
		var objTwo = this.initElement(null, this.cloneEObjWithContainers(reqMod2));

		this.testSimilarity(objOne, objTwo, ModulesPackage.Literals.REQUIRES_MODULE_DIRECTIVE__REQUIRED_MODULE);
	}

	@Test
	public void testRequiredModuleNullCheck() {
		this.testSimilarityNullCheck(this.initElement(null, this.cloneEObjWithContainers(reqMod1)),
				new RequiresModuleDirectiveInitialiser(), false,
				ModulesPackage.Literals.REQUIRES_MODULE_DIRECTIVE__REQUIRED_MODULE);
	}
}
