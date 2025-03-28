package cipm.consistency.fitests.similarity.jamopp.unittests.complextests;

import org.emftext.language.java.types.TypesPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.initialisers.jamopp.classifiers.ClassInitialiser;
import cipm.consistency.initialisers.jamopp.generics.QualifiedTypeArgumentInitialiser;
import cipm.consistency.initialisers.jamopp.generics.TypeParameterInitialiser;
import cipm.consistency.initialisers.jamopp.instantiations.NewConstructorCallInitialiser;
import cipm.consistency.initialisers.jamopp.types.ClassifierReferenceInitialiser;

@Disabled("Until the case given here is handled properly")
public class GenericInstantiationTest extends AbstractJaMoPPSimilarityTest {
	@Test
	public void test() {
		var clsInit = new ClassInitialiser();
		var clsRefInit = new ClassifierReferenceInitialiser();
		var tpInit = new TypeParameterInitialiser();
		var taInit = new QualifiedTypeArgumentInitialiser();

		// Initialise generic class: cls<T extends extCls>

		var tpExtendsClsName = "extCls";
		var tpExtendsCls = clsInit.instantiate();
		Assertions.assertTrue(clsInit.setName(tpExtendsCls, tpExtendsClsName));

		var tpExtendsClsRef = clsRefInit.instantiate();
		Assertions.assertTrue(clsRefInit.setTarget(tpExtendsClsRef, tpExtendsCls));

		var tpName = "T";
		var tp = tpInit.instantiate();
		Assertions.assertTrue(tpInit.setName(tp, tpName));
		Assertions.assertTrue(tpInit.addExtendType(tp, tpExtendsClsRef));

		var clsName = "cls";
		var cls = clsInit.instantiate();
		Assertions.assertTrue(clsInit.setName(cls, clsName));
		Assertions.assertTrue(clsInit.addTypeParameter(cls, tp));

		// ncc1Cls extends extCls
		var ncc1ClsName = "ncc1Cls";
		var ncc1Cls = clsInit.instantiate();
		Assertions.assertTrue(clsInit.setName(ncc1Cls, ncc1ClsName));
		var extendsClsRefNCC1 = clsRefInit.instantiate();
		Assertions.assertTrue(clsRefInit.setTarget(extendsClsRefNCC1, tpExtendsCls));
		Assertions.assertTrue(clsInit.setExtends(ncc1Cls, extendsClsRefNCC1));

		// ncc2Cls extends extCls
		var ncc2ClsName = "ncc2Cls";
		var ncc2Cls = clsInit.instantiate();
		Assertions.assertTrue(clsInit.setName(ncc2Cls, ncc2ClsName));
		var extendsClsRefNCC2 = clsRefInit.instantiate();
		Assertions.assertTrue(clsRefInit.setTarget(extendsClsRefNCC2, tpExtendsCls));
		Assertions.assertTrue(clsInit.setExtends(ncc2Cls, extendsClsRefNCC2));

		// Initialise ncc ta classifierRefs

		var ncc1TAClsRef = clsRefInit.instantiate();
		Assertions.assertTrue(clsRefInit.setTarget(ncc1TAClsRef, ncc1Cls));

		var ncc2TAClsRef = clsRefInit.instantiate();
		Assertions.assertTrue(clsRefInit.setTarget(ncc2TAClsRef, ncc2Cls));

		// Initialise ncc classifierRefs

		var ncc1ClsRefTA = taInit.instantiate();
		Assertions.assertTrue(taInit.setTypeReference(ncc1ClsRefTA, ncc1TAClsRef));

		var ncc2ClsRefTA = taInit.instantiate();
		Assertions.assertTrue(taInit.setTypeReference(ncc2ClsRefTA, ncc2TAClsRef));

		// Ref to "cls<ncc1Cls>"
		var ncc1ClsRef = clsRefInit.instantiate();
		Assertions.assertTrue(clsRefInit.setTarget(ncc1ClsRef, cls));
		Assertions.assertTrue(clsRefInit.addTypeArgument(ncc1ClsRef, ncc1ClsRefTA));

		// Ref to "cls<ncc2Cls>"
		var ncc2ClsRef = clsRefInit.instantiate();
		Assertions.assertTrue(clsRefInit.setTarget(ncc2ClsRef, cls));
		Assertions.assertTrue(clsRefInit.addTypeArgument(ncc2ClsRef, ncc2ClsRefTA));

		// Initialise nccs: new cls<ncc1Cls>, new cls<ncc2Cls>

		var nccInit = new NewConstructorCallInitialiser();

		var ncc1 = nccInit.instantiate();
		Assertions.assertTrue(nccInit.setTypeReference(ncc1, ncc1ClsRef));

		var ncc2 = nccInit.instantiate();
		Assertions.assertTrue(nccInit.setTypeReference(ncc2, ncc2ClsRef));

		var expectedVal = this.getExpectedSimilarityResult(ncc1, TypesPackage.Literals.TYPED_ELEMENT__TYPE_REFERENCE);
		Assertions.assertFalse(expectedVal);

		Assertions.assertEquals(ncc1Cls.getExtends().getTarget(), ncc2Cls.getExtends().getTarget());
		Assertions.assertTrue(this.isSimilar(ncc1Cls.getExtends(), ncc2Cls.getExtends()));
		Assertions.assertFalse(this.isSimilar(ncc1Cls, ncc2Cls));

		Assertions.assertFalse(this.isSimilar(ncc1TAClsRef, ncc2TAClsRef));
		Assertions.assertFalse(this.isSimilar(ncc1ClsRefTA, ncc2ClsRefTA));

		// TODO: Check for TypeArguments in caseClassifierReference
		// TODO: Check for TypeReference in caseNewConstructorCall
		// TODO: Change similarity values accordingly once fixed
		
		Assertions.assertEquals(ncc1ClsRef.getTarget(), ncc2ClsRef.getTarget());
		Assertions.assertFalse(this.areSimilar(ncc1ClsRef.getTypeArguments(), ncc2ClsRef.getTypeArguments()));
		Assertions.assertFalse(this.isSimilar(ncc1.getTypeReference(), ncc2.getTypeReference()));

		this.testSimilarity(ncc1, ncc2, expectedVal);
	}
}
