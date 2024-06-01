package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.annotations.AnnotationValue;
import org.emftext.language.java.members.InterfaceMethod;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.members.InterfaceMethodInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesAnnotationValues;

public class InterfaceMethodTest extends EObjectSimilarityTest implements UsesAnnotationValues {
	protected InterfaceMethod initElement(AnnotationValue av) {
		var imInit = new InterfaceMethodInitialiser();
		var im = imInit.instantiate();
		imInit.minimalInitialisation(im);
		imInit.setDefaultValue(im, av);
		return im;
	}
	
	@Test
	public void testDefaultValue() {
		this.setResourceFileTestIdentifier("testDefaultValue");
		
		var objOne = this.initElement(this.createNullVal());
		var objTwo = this.initElement(this.createStringRefVal("strval"));
		
		this.compareX(objOne, objTwo, false);
	}
}
