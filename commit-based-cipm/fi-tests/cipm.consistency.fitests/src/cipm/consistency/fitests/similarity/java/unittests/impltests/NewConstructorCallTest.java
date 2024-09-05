package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.classifiers.AnonymousClass;
import org.emftext.language.java.instantiations.NewConstructorCall;
import org.emftext.language.java.instantiations.InstantiationsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.instantiations.NewConstructorCallInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesAnonymousClasses;

public class NewConstructorCallTest extends EObjectSimilarityTest implements UsesAnonymousClasses {
	protected NewConstructorCall initElement(AnonymousClass anonymousCls) {
		var nccInit = new NewConstructorCallInitialiser();
		var ncc = nccInit.instantiate();

		// FIXME: Move to complex tests later
//		var clsInit = new ClassInitialiser();
//		var cls = clsInit.instantiate();
//
//		var trefInit = new ClassifierReferenceInitialiser();
//		var tref = trefInit.instantiate();
//
//		Assertions.assertTrue(trefInit.setTarget(tref, cls));
//		Assertions.assertTrue(nccInit.setTypeReference(ncc, tref));
		Assertions.assertTrue(nccInit.setAnonymousClass(ncc, anonymousCls));
		return ncc;
	}

	@Test
	public void testAnonymousClass() {
		var objOne = this.initElement(this.createMinimalAnonymousClassWithMethod("met1"));
		var objTwo = this.initElement(this.createMinimalAnonymousClassWithMethod("met2"));

		this.testSimilarity(objOne, objTwo, InstantiationsPackage.Literals.NEW_CONSTRUCTOR_CALL__ANONYMOUS_CLASS);
	}

	@Test
	public void testAnonymousClassNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.createMinimalAnonymousClassWithMethod("met1")), new NewConstructorCallInitialiser(), false, InstantiationsPackage.Literals.NEW_CONSTRUCTOR_CALL__ANONYMOUS_CLASS);
	}
}
