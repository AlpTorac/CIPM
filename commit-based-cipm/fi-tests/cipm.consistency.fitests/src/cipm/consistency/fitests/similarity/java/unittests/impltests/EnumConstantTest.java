package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.classifiers.AnonymousClass;
import org.emftext.language.java.members.EnumConstant;
import org.emftext.language.java.members.MembersPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.members.EnumConstantInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesAnonymousClasses;

public class EnumConstantTest extends EObjectSimilarityTest implements UsesAnonymousClasses {
	protected EnumConstant initElement(AnonymousClass cls) {
		var ecInit = new EnumConstantInitialiser();
		var ec = ecInit.instantiate();
		Assertions.assertTrue(ecInit.setAnonymousClass(ec, cls));
		return ec;
	}

	@Test
	public void testAnonymousClass() {
		this.setResourceFileTestIdentifier("testAnonymousClass");

		var objOne = this.initElement(this.createMinimalAnonymousClassWithMethod("met1"));
		var objTwo = this.initElement(this.createMinimalAnonymousClassWithMethod("met2"));

		this.testSimilarity(objOne, objTwo, MembersPackage.Literals.ENUM_CONSTANT__ANONYMOUS_CLASS);
	}
}
