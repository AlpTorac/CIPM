package cipm.consistency.fitests.similarity.eobject.java.unittests.impltests;

import org.emftext.language.java.imports.ImportsPackage;
import org.emftext.language.java.imports.StaticMemberImport;
import org.emftext.language.java.references.ReferenceableElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.eobject.java.AbstractEObjectJavaSimilarityTest;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.imports.StaticMemberImportInitialiser;
import cipm.consistency.fitests.similarity.eobject.java.unittests.UsesMethods;

public class StaticMemberImportTest extends AbstractEObjectJavaSimilarityTest implements UsesMethods {
	protected StaticMemberImport initElement(ReferenceableElement[] staticMems) {
		var smiInit = new StaticMemberImportInitialiser();
		var smi = smiInit.instantiate();
		Assertions.assertTrue(smiInit.addStaticMembers(smi, staticMems));
		return smi;
	}

	@Test
	public void testStaticMember() {
		var objOne = this.initElement(new ReferenceableElement[] { this.createMinimalClsMethodWithNullReturn("met1") });
		var objTwo = this.initElement(new ReferenceableElement[] { this.createMinimalClsMethodWithNullReturn("met2") });

		this.testSimilarity(objOne, objTwo, ImportsPackage.Literals.STATIC_MEMBER_IMPORT__STATIC_MEMBERS);
	}

	@Test
	public void testStaticMemberSize() {
		var objOne = this.initElement(new ReferenceableElement[] { this.createMinimalClsMethodWithNullReturn("met1"),
				this.createMinimalClsMethodWithNullReturn("met2") });
		var objTwo = this.initElement(new ReferenceableElement[] { this.createMinimalClsMethodWithNullReturn("met1") });

		this.testSimilarity(objOne, objTwo, ImportsPackage.Literals.STATIC_MEMBER_IMPORT__STATIC_MEMBERS);
	}

	@Test
	public void testStaticMemberNullCheck() {
		this.testSimilarityNullCheck(
				this.initElement(new ReferenceableElement[] { this.createMinimalClsMethodWithNullReturn("met1") }),
				new StaticMemberImportInitialiser(), false,
				ImportsPackage.Literals.STATIC_MEMBER_IMPORT__STATIC_MEMBERS);
	}
}
