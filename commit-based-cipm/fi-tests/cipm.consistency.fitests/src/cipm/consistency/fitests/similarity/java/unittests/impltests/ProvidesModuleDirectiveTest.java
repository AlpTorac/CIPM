package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.modules.ProvidesModuleDirective;
import org.emftext.language.java.modules.ModulesPackage;
import org.emftext.language.java.types.TypeReference;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.modules.ProvidesModuleDirectiveInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesTypeReferences;

public class ProvidesModuleDirectiveTest extends EObjectSimilarityTest implements UsesTypeReferences {
	protected ProvidesModuleDirective initElement(TypeReference[] trefs) {
		var pmdInit = new ProvidesModuleDirectiveInitialiser();
		var pmd = pmdInit.instantiate();
		pmdInit.minimalInitialisation(pmd);
		pmdInit.addServiceProviders(pmd, trefs);
		return pmd;
	}
	
	@Test
	public void testServiceProvider() {
		this.setResourceFileTestIdentifier("testServiceProvider");
		
		var objOne = this.initElement(new TypeReference[] {this.createMinimalClsRef("cls1")});
		var objTwo = this.initElement(new TypeReference[] {this.createMinimalClsRef("cls2")});
		
		this.testX(objOne, objTwo, ModulesPackage.Literals.PROVIDES_MODULE_DIRECTIVE__SERVICE_PROVIDERS);
	}
}
