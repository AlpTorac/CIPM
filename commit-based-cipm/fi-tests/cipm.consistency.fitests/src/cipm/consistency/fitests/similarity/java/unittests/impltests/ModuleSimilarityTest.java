package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.containers.Module;
import org.emftext.language.java.containers.Package;
import org.emftext.language.java.modules.ModuleDirective;

import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.containers.ModuleInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.modifiers.OpenInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesModuleDirectives;

public class ModuleSimilarityTest extends EObjectSimilarityTest implements UsesModuleDirectives {
	protected Module initElement(Package[] pacs, ModuleDirective[] targets, boolean isOpen) {
		var initialiser = new ModuleInitialiser();
		Module result = initialiser.instantiate();
		initialiser.minimalInitialisation(result);
		
		initialiser.addPackages(result, pacs);
		initialiser.addTargets(result, targets);
		
		if (isOpen) {
			initialiser.initialiseOpen(result,
					new OpenInitialiser().instantiate());
		}
		
		return result;
	}
	
	@Test
	public void testOpen() {
		this.setResourceFileTestIdentifier("testOpen");
		
		var objOne = this.initElement(null, null, true);
		var objTwo = this.initElement(null, null, false);
		
		this.testX(objOne, objTwo, false);
	}
	
	@Test
	public void testPackages() {
		this.setResourceFileTestIdentifier("testPackages");
		
		var objOne = this.initElement(new Package[] {
				this.createMinimalPackage(new String[] {"ns1"})
		}, null, false);
		var objTwo = this.initElement(new Package[] {
				this.createMinimalPackage(new String[] {"ns2"})
		}, null, false);
		
		this.testX(objOne, objTwo, false);
	}

	public void testModuleDirectives() {
		this.setResourceFileTestIdentifier("testModuleDirectives");
		
		var objOne = this.initElement(null, new ModuleDirective[] {
				this.createMinimalEMD(new String[] {"ns1"})
		}, false);
		var objTwo = this.initElement(null, new ModuleDirective[] {
				this.createMinimalOMD(new String[] {"ns1"})
		}, false);
		
		this.testX(objOne, objTwo, false);
	}
}
