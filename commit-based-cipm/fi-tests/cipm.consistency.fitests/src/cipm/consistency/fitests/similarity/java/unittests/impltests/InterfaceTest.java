package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.classifiers.Interface;
import org.emftext.language.java.types.TypeReference;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.InterfaceInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesTypeReferences;

public class InterfaceTest extends EObjectSimilarityTest implements UsesTypeReferences {
	protected Interface initElement(TypeReference[] defExts, TypeReference[] exts) {
		var intfcInit = new InterfaceInitialiser();
		var intfc = intfcInit.instantiate();
		intfcInit.minimalInitialisation(intfc);
		intfcInit.addDefaultExtends(intfc, defExts);
		intfcInit.addExtends(intfc, exts);
		return intfc;
	}
	
	@Test
	public void testDefaultExtends() {
		this.setResourceFileTestIdentifier("testDefaultExtends");
		
		var objOne = this.initElement(new TypeReference[] {this.createMinimalClsRef("cls1")}, null);
		var objTwo = this.initElement(new TypeReference[] {this.createMinimalClsRef("cls2")}, null);
		
		this.compareX(objOne, objTwo, false);
	}
	
	@Test
	public void testExtends() {
		this.setResourceFileTestIdentifier("testExtends");
		
		var objOne = this.initElement(null, new TypeReference[] {this.createMinimalClsRef("cls1")});
		var objTwo = this.initElement(null, new TypeReference[] {this.createMinimalClsRef("cls2")});
		
		this.compareX(objOne, objTwo, false);
	}
}