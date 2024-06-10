package cipm.consistency.fitests.similarity.java.unittests.impltests;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.ClassInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesTypeReferences;

import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.types.TypeReference;
import org.junit.jupiter.api.Test;

public class ClassTest extends EObjectSimilarityTest implements UsesTypeReferences {
	protected Class initElement(TypeReference defExt, TypeReference ext) {
		var clsInit = new ClassInitialiser();
		var cls = clsInit.instantiate();
		clsInit.minimalInitialisation(cls);
		clsInit.setDefaultExtends(cls, defExt);
		clsInit.setExtends(cls, ext);
		return cls;
	}
	
	@Test
	public void testDefaultExtends() {
		this.setResourceFileTestIdentifier("testDefaultExtends");
		
		var objOne = this.initElement(this.createMinimalClsRef("cls1"), null);
		var objTwo = this.initElement(this.createMinimalClsRef("cls2"), null);
		
		this.compareX(objOne, objTwo, false);
	}
	
	@Test
	public void testExtends() {
		this.setResourceFileTestIdentifier("testExtends");
		
		var objOne = this.initElement(null, this.createMinimalClsRef("cls1"));
		var objTwo = this.initElement(null, this.createMinimalClsRef("cls2"));
		
		this.compareX(objOne, objTwo, false);
	}
}