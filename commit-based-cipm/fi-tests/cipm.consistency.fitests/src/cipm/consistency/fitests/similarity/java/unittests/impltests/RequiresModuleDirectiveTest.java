package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.modifiers.ModuleRequiresModifier;
import org.emftext.language.java.modules.ModuleReference;
import org.emftext.language.java.modules.RequiresModuleDirective;
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
		rmdInit.minimalInitialisation(rmd);
		rmdInit.setModifier(rmd, mrm);
		rmdInit.setRequiredModule(rmd, mref);
		return rmd;
	}
	
	@Test
	public void testModifier() {
		this.setResourceFileTestIdentifier("testModifier");
		
		var objOne = this.initElement(this.createStatic(), null);
		var objTwo = this.initElement(this.createTransitive(), null);
		
		this.testX(objOne, objTwo, false);
	}
	
	@Test
	public void testRequiredModule() {
		this.setResourceFileTestIdentifier("testRequiredModule");
		
		var objOne = this.initElement(null, this.createMinimalModuleReference("mod1"));
		var objTwo = this.initElement(null, this.createMinimalModuleReference("mod2"));
		
		this.testX(objOne, objTwo, false);
	}
}
