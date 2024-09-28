package cipm.consistency.fitests.similarity.emftext.unittests.impltests;

import org.emftext.language.java.containers.Module;
import org.emftext.language.java.modules.ModuleReference;
import org.emftext.language.java.modules.ModulesPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.emftext.AbstractEMFTextSimilarityTest;
import cipm.consistency.fitests.similarity.emftext.unittests.UsesModules;
import cipm.consistency.initialisers.emftext.modules.ModuleReferenceInitialiser;

public class ModuleReferenceTest extends AbstractEMFTextSimilarityTest implements UsesModules {
	protected ModuleReference initElement(Module target) {
		var mrInit = new ModuleReferenceInitialiser();
		var mr = mrInit.instantiate();
		Assertions.assertTrue(mrInit.setTarget(mr, target));
		return mr;
	}

	@Test
	public void testTarget() {
		var objOne = this.initElement(this.createMinimalModule("mod1"));
		var objTwo = this.initElement(this.createMinimalModule("mod2"));

		this.testSimilarity(objOne, objTwo, ModulesPackage.Literals.MODULE_REFERENCE__TARGET);
	}

	@Test
	public void testTargetNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.createMinimalModule("mod1")),
				new ModuleReferenceInitialiser(), false, ModulesPackage.Literals.MODULE_REFERENCE__TARGET);
	}
}