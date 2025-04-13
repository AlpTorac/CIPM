package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.classifiers.AnonymousClass;
import org.emftext.language.java.classifiers.impl.AnonymousClassImpl;
import org.emftext.language.java.members.EnumConstant;
import org.emftext.language.java.members.MembersPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesAnonymousClasses;
import cipm.consistency.initialisers.jamopp.members.EnumConstantInitialiser;

public class EnumConstantTest extends AbstractJaMoPPSimilarityTest implements UsesAnonymousClasses {
	private AnonymousClass anonCls1;
	private AnonymousClass anonCls2;

	protected EnumConstant initElement(AnonymousClass anonCls) {
		var ecInit = new EnumConstantInitialiser();
		var ec = ecInit.instantiate();
		Assertions.assertTrue(ecInit.setAnonymousClass(ec, anonCls));
		return ec;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		anonCls1 = this.createMinimalAnonymousClass();
		/*
		 * Since there is currently no way to make AnonymousClass instances different,
		 * use an anonymous class instance to force difference.
		 */
		anonCls2 = new AnonymousClassImpl() {
		};
		Assertions.assertFalse(this.isSimilar(anonCls1, anonCls2));
	}

	@Test
	public void testAnonymousClass() {
		var objOne = this.initElement(this.cloneEObjWithContainers(anonCls1));
		var objTwo = this.initElement(this.cloneEObjWithContainers(anonCls2));

		this.testSimilarity(objOne, objTwo, MembersPackage.Literals.ENUM_CONSTANT__ANONYMOUS_CLASS);
	}

	@Test
	public void testAnonymousClassNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.cloneEObjWithContainers(anonCls1)),
				new EnumConstantInitialiser(), false, MembersPackage.Literals.ENUM_CONSTANT__ANONYMOUS_CLASS);
	}
}
