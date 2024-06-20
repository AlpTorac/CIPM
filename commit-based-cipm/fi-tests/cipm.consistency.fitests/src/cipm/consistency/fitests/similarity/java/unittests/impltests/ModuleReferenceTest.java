package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.containers.Module;
import org.emftext.language.java.modules.ModuleReference;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.modules.ModuleReferenceInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesModules;

public class ModuleReferenceTest extends EObjectSimilarityTest implements UsesModules {
	protected ModuleReference initElement(Module mod) {
		var mrInit = new ModuleReferenceInitialiser();
		var mr = mrInit.instantiate();
		mrInit.minimalInitialisation(mr);
		mrInit.setTarget(mr, mod);
		return mr;
	}
	
	@Test
	public void testTarget() {
		this.setResourceFileTestIdentifier("testTarget");
		
		var objOne = this.initElement(this.createMinimalModule("mod1"));
		var objTwo = this.initElement(this.createMinimalModule("mod2"));
		
		this.testX(objOne, objTwo, false);
	}
}
