package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.modifiers.ModuleRequiresModifier;
import org.emftext.language.java.modules.ModuleReference;
import org.emftext.language.java.modules.ModulesPackage;
import org.emftext.language.java.modules.RequiresModuleDirective;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.modules.RequiresModuleDirectiveInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesLiterals;
import cipm.consistency.fitests.similarity.java.unittests.UsesModuleReferences;

public class RequiresModuleDirectiveTest extends EObjectSimilarityTest implements UsesLiterals,
UsesModuleReferences {
	protected RequiresModuleDirective initElement(ModuleRequiresModifier mrm, ModuleReference mref) {
		var rmdInit = new RequiresModuleDirectiveInitialiser();
		var rmd = rmdInit.instantiate();
		Assertions.assertTrue(rmdInit.minimalInitialisation(rmd));
		Assertions.assertTrue(rmdInit.setModifier(rmd, mrm));
		Assertions.assertTrue(rmdInit.setRequiredModule(rmd, mref));
		return rmd;
	}
	
	@Test
	public void testModifier() {
		this.setResourceFileTestIdentifier("testModifier");
		
		var objOne = this.initElement(this.createStatic(), null);
		var objTwo = this.initElement(this.createTransitive(), null);
		
		this.testX(objOne, objTwo, ModulesPackage.Literals.REQUIRES_MODULE_DIRECTIVE__MODIFIER);
	}
	
	@Test
	public void testRequiredModule() {
		this.setResourceFileTestIdentifier("testRequiredModule");
		
		var objOne = this.initElement(null, this.createMinimalMR("mod1", new String[] {"ns1", "ns2"}));
		var objTwo = this.initElement(null, this.createMinimalMR("mod2", new String[] {"ns3", "ns4"}));
		
		this.testX(objOne, objTwo, ModulesPackage.Literals.REQUIRES_MODULE_DIRECTIVE__REQUIRED_MODULE);
	}
}
