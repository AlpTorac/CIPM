package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.containers.Module;
import org.emftext.language.java.containers.Package;
import org.emftext.language.java.classifiers.ConcreteClassifier;

import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.containers.PackageInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesConcreteClassifiers;
import cipm.consistency.fitests.similarity.java.unittests.UsesModules;

public class PackageSimilarityTest extends EObjectSimilarityTest implements UsesModules, UsesConcreteClassifiers {
	protected Package initElement(Module mod, ConcreteClassifier[] clss) {
		var initialiser = new PackageInitialiser();
		Package pac = initialiser.instantiate();
		initialiser.minimalInitialisation(pac);
		
		initialiser.initialiseModuleField(pac, mod);
		initialiser.addClassifiers(pac, clss);
		
		return pac;
	}
	
	@Test
	public void testModule() {
		this.setResourceFileTestIdentifier("testModule");
		
		var objOne = this.initElement(this.createMinimalModule("mod1"), null);
		var objTwo = this.initElement(this.createMinimalModule("mod2"), null);
		
		this.testX(objOne, objTwo, false);
	}
	
	@Test
	public void testClassifiers() {
		this.setResourceFileTestIdentifier("testClassifiers");
		
		var objOne = this.initElement(null, new ConcreteClassifier[] {
				this.createMinimalClass("cls1")
		});
		var objTwo = this.initElement(null, new ConcreteClassifier[] {
				this.createMinimalClass("cls2")
		});
		
		this.testX(objOne, objTwo, false);
	}
}
