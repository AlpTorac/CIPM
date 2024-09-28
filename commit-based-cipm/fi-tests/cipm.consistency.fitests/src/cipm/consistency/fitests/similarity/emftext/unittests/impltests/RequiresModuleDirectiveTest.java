package cipm.consistency.fitests.similarity.emftext.unittests.impltests;

import org.emftext.language.java.modifiers.ModuleRequiresModifier;
import org.emftext.language.java.modules.ModuleReference;
import org.emftext.language.java.modules.ModulesPackage;
import org.emftext.language.java.modules.RequiresModuleDirective;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.emftext.AbstractEMFTextSimilarityTest;
import cipm.consistency.fitests.similarity.emftext.unittests.UsesLiterals;
import cipm.consistency.fitests.similarity.emftext.unittests.UsesModuleReferences;
import cipm.consistency.initialisers.emftext.modules.RequiresModuleDirectiveInitialiser;

public class RequiresModuleDirectiveTest extends AbstractEMFTextSimilarityTest implements UsesLiterals, UsesModuleReferences {
	protected RequiresModuleDirective initElement(ModuleRequiresModifier modif, ModuleReference reqMod) {
		var rmdInit = new RequiresModuleDirectiveInitialiser();
		var rmd = rmdInit.instantiate();
		Assertions.assertTrue(rmdInit.setModifier(rmd, modif));
		Assertions.assertTrue(rmdInit.setRequiredModule(rmd, reqMod));
		return rmd;
	}

	@Test
	public void testModifier() {
		var objOne = this.initElement(this.createStatic(), null);
		var objTwo = this.initElement(this.createTransitive(), null);

		this.testSimilarity(objOne, objTwo, ModulesPackage.Literals.REQUIRES_MODULE_DIRECTIVE__MODIFIER);
	}

	@Test
	public void testModifierNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.createStatic(), null),
				new RequiresModuleDirectiveInitialiser(), false,
				ModulesPackage.Literals.REQUIRES_MODULE_DIRECTIVE__MODIFIER);
	}

	@Test
	public void testRequiredModule() {
		var objOne = this.initElement(null, this.createMinimalMR("mod1", new String[] { "ns1", "ns2" }));
		var objTwo = this.initElement(null, this.createMinimalMR("mod2", new String[] { "ns3", "ns4" }));

		this.testSimilarity(objOne, objTwo, ModulesPackage.Literals.REQUIRES_MODULE_DIRECTIVE__REQUIRED_MODULE);
	}

	@Test
	public void testRequiredModuleNullCheck() {
		this.testSimilarityNullCheck(
				this.initElement(null, this.createMinimalMR("mod1", new String[] { "ns1", "ns2" })),
				new RequiresModuleDirectiveInitialiser(), false,
				ModulesPackage.Literals.REQUIRES_MODULE_DIRECTIVE__REQUIRED_MODULE);
	}
}