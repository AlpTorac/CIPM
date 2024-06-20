package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.classifiers.AnonymousClass;
import org.emftext.language.java.instantiations.NewConstructorCall;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.instantiations.NewConstructorCallInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesAnonymousClasses;

public class NewConstructorCallTest extends EObjectSimilarityTest implements UsesAnonymousClasses {
	protected NewConstructorCall initElement(AnonymousClass ac) {
		var nccInit = new NewConstructorCallInitialiser();
		var ncc = nccInit.instantiate();
		nccInit.minimalInitialisation(ncc);
		nccInit.setAnonymousClass(ncc, ac);
		return ncc;
	}
	
	@Test
	public void testAnonymousClass() {
		this.setResourceFileTestIdentifier("testAnonymousClass");
		
		var objOne = this.initElement(this.createMinimalAnonymousClassWithMethod("met1"));
		var objTwo = this.initElement(this.createMinimalAnonymousClassWithMethod("met2"));
		
		this.testX(objOne, objTwo, false);
	}
}
