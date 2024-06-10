package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.generics.SuperTypeArgument;
import org.emftext.language.java.types.TypeReference;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.generics.SuperTypeArgumentInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesTypeReferences;

public class SuperTypeArgumentTest extends EObjectSimilarityTest implements UsesTypeReferences {
	protected SuperTypeArgument initElement(TypeReference tref) {
		var staInit = new SuperTypeArgumentInitialiser();
		var sta = staInit.instantiate();
		staInit.minimalInitialisation(sta);
		staInit.setSuperType(sta, tref);
		return sta;
	}
	
	@Test
	public void testSuperType() {
		this.setResourceFileTestIdentifier("testSuperType");
		
		var objOne = this.initElement(this.createMinimalClsRef("cls1"));
		var objTwo = this.initElement(this.createMinimalClsRef("cls2"));
		
		this.compareX(objOne, objTwo, false);
	}
}