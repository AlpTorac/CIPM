package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.classifiers.Enumeration;
import org.emftext.language.java.members.EnumConstant;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.EnumerationInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesEnumConstants;

public class EnumerationTest extends EObjectSimilarityTest implements UsesEnumConstants {
	protected Enumeration initElement(EnumConstant[] csts) {
		var enmInit = new EnumerationInitialiser();
		var enm = enmInit.instantiate();
		enmInit.minimalInitialisation(enm);
		enmInit.addConstants(enm, csts);
		return enm;
	}
	
	@Test
	public void testConstant() {
		this.setResourceFileTestIdentifier("testConstant");
		
		var objOne = this.initElement(new EnumConstant[] {this.createMinimalEnumConstant("cst1")});
		var objTwo = this.initElement(new EnumConstant[] {this.createMinimalEnumConstant("cst2")});
		
		this.compareX(objOne, objTwo, false);
	}
}
