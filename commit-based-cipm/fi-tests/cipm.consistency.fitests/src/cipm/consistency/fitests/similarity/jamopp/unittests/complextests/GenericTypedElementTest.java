package cipm.consistency.fitests.similarity.jamopp.unittests.complextests;

import java.util.stream.Stream;

import org.emftext.language.java.types.TypesPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.initialisers.jamopp.classifiers.ClassInitialiser;
import cipm.consistency.initialisers.jamopp.generics.QualifiedTypeArgumentInitialiser;
import cipm.consistency.initialisers.jamopp.generics.TypeParameterInitialiser;
import cipm.consistency.initialisers.jamopp.types.ClassifierReferenceInitialiser;
import cipm.consistency.initialisers.jamopp.types.ITypedElementInitialiser;

@Disabled("Until the case given here is handled properly")
public class GenericTypedElementTest extends AbstractJaMoPPSimilarityTest {
	/**
	 * @return An instance of each sub-type of ITypedElementInitialiser as Arguments
	 *         instance.
	 */
	private static Stream<Arguments> genTestParams() {
		return AbstractJaMoPPSimilarityTest.getNonAdaptedInitialiserArgumentsFor(ITypedElementInitialiser.class);
	}

	/**
	 * Ensures that similarity checking handles typed elements that have a similar
	 * type reference with different type arguments as expected. For each sub-type
	 * of {@link TypedElement}, constructs 2 instances with similar type references
	 * that have distinct type arguments and compares them. <br>
	 * <br>
	 * Ex: Assume {@code cls<T extends extCls>} and
	 * {@code subExtCls1 extends extCls} and {@code subExtCls2 extends extCls}. Then
	 * the test setup for {@link NewConstructorCall} is:
	 * {@code isSimilar(new cls<subExtCls1>, new cls<subExtCls2>)}.
	 * 
	 * TODO: Check for TypeArguments in caseClassifierReference
	 * 
	 * TODO: Check for TypeReference in caseNewConstructorCall
	 * 
	 * TODO: Change similarity values accordingly once fixed
	 */
	@ParameterizedTest(name = "{1}")
	@MethodSource("genTestParams")
	public void testGenericTypedElement(ITypedElementInitialiser typedElemInit) {
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

		/*
		 * Initialise the classifiers for type arguments. i.e. classifiers
		 * "typedElemXCls" that are used in "cls<typedElem1Cls>" and
		 * "cls<typedElem2Cls>"
		 */
		var typedElem1ClsName = "typedElem1Cls";
		var typedElem1Cls = clsInit.instantiate();
		Assertions.assertTrue(clsInit.setName(typedElem1Cls, typedElem1ClsName));
		var extendsClsReftypedElem1 = clsRefInit.instantiate();
		Assertions.assertTrue(clsRefInit.setTarget(extendsClsReftypedElem1, tpExtendsCls));
		Assertions.assertTrue(clsInit.setExtends(typedElem1Cls, extendsClsReftypedElem1));

		var typedElem2ClsName = "typedElem2Cls";
		var typedElem2Cls = clsInit.instantiate();
		Assertions.assertTrue(clsInit.setName(typedElem2Cls, typedElem2ClsName));
		var extendsClsReftypedElem2 = clsRefInit.instantiate();
		Assertions.assertTrue(clsRefInit.setTarget(extendsClsReftypedElem2, tpExtendsCls));
		Assertions.assertTrue(clsInit.setExtends(typedElem2Cls, extendsClsReftypedElem2));

		/*
		 * Initialise classifier references for type arguments. i.e. references
		 * "typedElemXCls" in "cls<typedElem1Cls>" and "cls<typedElem2Cls>"
		 */
		var typedElem1TAClsRef = clsRefInit.instantiate();
		Assertions.assertTrue(clsRefInit.setTarget(typedElem1TAClsRef, typedElem1Cls));

		var typedElem2TAClsRef = clsRefInit.instantiate();
		Assertions.assertTrue(clsRefInit.setTarget(typedElem2TAClsRef, typedElem2Cls));

		/*
		 * Initialise type arguments "cls<typedElem1Cls>" and "cls<typedElem2Cls>" that
		 * are used by the typed elements.
		 */

		var typedElem1ClsRefTA = taInit.instantiate();
		Assertions.assertTrue(taInit.setTypeReference(typedElem1ClsRefTA, typedElem1TAClsRef));

		var typedElem2ClsRefTA = taInit.instantiate();
		Assertions.assertTrue(taInit.setTypeReference(typedElem2ClsRefTA, typedElem2TAClsRef));

		/*
		 * Initialise references to type arguments "cls<typedElem1Cls>" and
		 * "cls<typedElem2Cls>" that are used by the typed elements.
		 */

		var typedElem1ClsRef = clsRefInit.instantiate();
		Assertions.assertTrue(clsRefInit.setTarget(typedElem1ClsRef, cls));
		Assertions.assertTrue(clsRefInit.addTypeArgument(typedElem1ClsRef, typedElem1ClsRefTA));

		var typedElem2ClsRef = clsRefInit.instantiate();
		Assertions.assertTrue(clsRefInit.setTarget(typedElem2ClsRef, cls));
		Assertions.assertTrue(clsRefInit.addTypeArgument(typedElem2ClsRef, typedElem2ClsRefTA));

		/*
		 * Initialise typed elements with type references. i.e. typedElem1 with
		 * cls<typedElem1Cls>, typedElem2 with cls<typedElem2Cls>
		 */
		var typedElem1 = typedElemInit.instantiate();
		Assertions.assertTrue(typedElemInit.setTypeReference(typedElem1, typedElem1ClsRef));

		var typedElem2 = typedElemInit.instantiate();
		Assertions.assertTrue(typedElemInit.setTypeReference(typedElem2, typedElem2ClsRef));

		var expectedVal = this.getExpectedSimilarityResult(typedElem1,
				TypesPackage.Literals.TYPED_ELEMENT__TYPE_REFERENCE);
		Assertions.assertFalse(expectedVal);

		Assertions.assertEquals(typedElem1Cls.getExtends().getTarget(), typedElem2Cls.getExtends().getTarget());
		Assertions.assertTrue(this.isSimilar(typedElem1Cls.getExtends(), typedElem2Cls.getExtends()));
		Assertions.assertFalse(this.isSimilar(typedElem1Cls, typedElem2Cls));

		Assertions.assertFalse(this.isSimilar(typedElem1TAClsRef, typedElem2TAClsRef));
		Assertions.assertFalse(this.isSimilar(typedElem1ClsRefTA, typedElem2ClsRefTA));

		Assertions.assertEquals(typedElem1ClsRef.getTarget(), typedElem2ClsRef.getTarget());
		Assertions
				.assertFalse(this.areSimilar(typedElem1ClsRef.getTypeArguments(), typedElem2ClsRef.getTypeArguments()));
		Assertions.assertFalse(this.isSimilar(typedElem1.getTypeReference(), typedElem2.getTypeReference()));

		this.testSimilarity(typedElem1, typedElem2, expectedVal);
	}
}
