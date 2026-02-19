package cipm.consistency.fluentapi.test.metamodel;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EcorePackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.api.ApiFactory;
import cipm.consistency.fluentapi.gen.FluentAPIGeneralParameterGenerator;
import cipm.consistency.fluentapi.gen.metamodels.java.FluentAPIJavaMetamodelFeatureFilter;
import cipm.consistency.fluentapi.gen.metamodels.java.FluentAPIJavaMetamodelPackageProvider;
import cipm.consistency.fluentapi.gen.rootapi.FluentAPIRootAPIConstants;
import cipm.consistency.fluentapi.test.AbstractFluentAPITest;

public class FluentAPIRootAPIGenerationTest extends AbstractFluentAPITest {

	@BeforeAll
	public static void setUpBeforeAll() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		FluentAPIGenerationTestUtil.setAPI(api);
		FluentAPIGenerationTestUtil.setElemEClsToInitEClsFunc((eCls) -> api.getInitialisationForX(eCls).eClass());
		FluentAPIGenerationTestUtil.setFeatureFilter(new FluentAPIJavaMetamodelFeatureFilter());
		FluentAPIGenerationTestUtil.setPackageProvider(new FluentAPIJavaMetamodelPackageProvider());
	}

	// TODO Add tests for other generated methods in API
	// TODO Include all generated EClasses

	// TODO Refactor this method
	private void methodTestTemplate(FluentAPIMethodTestData testData) {
		Assertions.assertNotEquals(0, testData.geteClssToCheckFor().size());

		for (var eCls : testData.geteClssToCheckFor()) {
			var paramNames = testData.getParamNames(eCls);
			var paramTypes = testData.getParamTypes(eCls);
			var expectMultiValueVariants = testData.getExpectMultiValueVariants(eCls);
			var expectBigNumberVariants = testData.getExpectBigNumberVariants(eCls);
			if (paramNames.size() != paramTypes.size())
				throw new IllegalArgumentException();

			var currentEClssOpName = testData.getMethodName(eCls);
			var currentEClssOps = FluentAPIGenerationTestUtil.getAllAPIOps().stream()
					.filter((op) -> op.getName().equals(currentEClssOpName))
					.filter((op) -> op.getEParameters().size() == paramNames.size())
//					.filter((op) -> op.getEType().equals(returnTypeOfOpFunc.apply(eCls))
					.collect(Collectors.toList());

			// Ensure that the original method is present
			Assertions.assertTrue(
					currentEClssOps.stream().anyMatch((op) -> assertParamsEqual(op, paramNames, paramTypes)));

			if (expectMultiValueVariants) {
				Assertions.assertEquals(3, currentEClssOps.size(), "For eCls " + eCls.getName());
				Assertions.assertTrue(currentEClssOps.stream()
						.anyMatch((op) -> assertArrayValuedParamsEqual(op, paramNames, paramTypes)));
				Assertions.assertTrue(currentEClssOps.stream()
						.anyMatch((op) -> assertCollectionValuedParamsEqual(op, paramNames, paramTypes)));
			} else if (expectBigNumberVariants) {
				Assertions.assertEquals(3, currentEClssOps.size(), "For eCls " + eCls.getName());
				Assertions.assertTrue(currentEClssOps.stream().anyMatch((op) -> assertParamsEqual(op, paramNames,
						paramTypes.stream()
								.map((t) -> t == EcorePackage.Literals.EBIG_INTEGER ? EcorePackage.Literals.EINT : t)
								.collect(Collectors.toList()))));
				Assertions.assertTrue(currentEClssOps.stream()
						.anyMatch((op) -> assertParamsEqual(op, paramNames, paramTypes.stream()
								.map((t) -> t == EcorePackage.Literals.EBIG_INTEGER ? EcorePackage.Literals.ELONG : t)
								.collect(Collectors.toList()))));
				Assertions.assertTrue(currentEClssOps.stream()
						.anyMatch((op) -> assertParamsEqual(op, paramNames, paramTypes.stream()
								.map((t) -> t == EcorePackage.Literals.EBIG_DECIMAL ? EcorePackage.Literals.EFLOAT : t)
								.collect(Collectors.toList()))));
				Assertions.assertTrue(currentEClssOps.stream()
						.anyMatch((op) -> assertParamsEqual(op, paramNames, paramTypes.stream()
								.map((t) -> t == EcorePackage.Literals.EBIG_DECIMAL ? EcorePackage.Literals.EDOUBLE : t)
								.collect(Collectors.toList()))));
			} else {
				Assertions.assertEquals(1, currentEClssOps.size(), "For eCls " + eCls.getName());
				Assertions.assertTrue(assertParamsEqual(currentEClssOps.get(0), paramNames, paramTypes));
			}
		}
	}

	private boolean assertParamsEqual(EOperation op, List<String> expectedParamNames,
			List<EClassifier> expectedParamTypes) {
		for (int i = 0; i < expectedParamNames.size(); i++) {
			var currentParam = op.getEParameters().get(i);
			if (!expectedParamNames.get(i).equals(currentParam.getName()))
				return false;
			// TODO Find out if it is possible to type check, currently the return type of
			// the op is a proxy object
//			Assertions.assertEquals(expectedParamTypes.get(i), currentParam.getEType());
		}
		return true;
	}

	private boolean assertArrayValuedParamsEqual(EOperation op, List<String> expectedParamNames,
			List<EClassifier> expectedParamTypes) {
		for (int i = 0; i < expectedParamNames.size(); i++) {
			var currentParam = op.getEParameters().get(i);
			if (!expectedParamNames.get(i).equals(currentParam.getName()))
				return false;
			// TODO Find out if it is possible to type check, currently the return type of
			// the op is a proxy object
//			Assertions.assertEquals(expectedParamTypes.get(i), currentParam.getEType());
		}
		return true;
	}

	private boolean assertCollectionValuedParamsEqual(EOperation op, List<String> expectedParamNames,
			List<EClassifier> expectedParamTypes) {
		for (int i = 0; i < expectedParamNames.size(); i++) {
			var currentParam = op.getEParameters().get(i);
			if (!expectedParamNames.get(i).equals(currentParam.getName()))
				return false;
			// TODO Find out if it is possible to type check, currently the return type of
			// the op is a proxy object
//			Assertions.assertEquals(expectedParamTypes.get(i), currentParam.getEType());
		}
		return true;
	}

	/**
	 * Checks whether{@code createNewX() : X} methods for all supported EClasses
	 * exist in API
	 */
	@Test
	public void methodTest_API_CreateNewX() {

		var testData = new FluentAPIMethodTestData();
		testData.seteClssToCheckFor(FluentAPIGenerationTestUtil.getAllSupportedConcreteEClss());
		testData.setMethodNamePrefix((eCls) -> String
				.format(FluentAPIRootAPIConstants.getFluentAPIRootAPICreateNewXMethodNameTemplate(), eCls.getName()));
		methodTestTemplate(testData);
	}

	/**
	 * Checks whether {@code newX() : XInitialisation} methods for all supported
	 * EClasses exist in API
	 */
	@Test
	public void methodTest_API_NewX_WithoutParameters() {

		var testData = new FluentAPIMethodTestData();
		testData.setMethodNamePrefix((eCls) -> String
				.format(FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodNameTemplate(), eCls.getName()));
		testData.setReturnTypeOfOp(FluentAPIGenerationTestUtil.getElemEClsToInitEClsFunc());
		testData.seteClssToCheckFor(FluentAPIGenerationTestUtil.getAllSupportedConcreteEClss());
		methodTestTemplate(testData);
	}

	/**
	 * Checks whether {@code newX() : X} methods for all supported EClasses (without
	 * any modifiable features according to the metamodel feature filter) exist in
	 * API
	 */
	@Test
	public void methodTest_API_NewX_WithoutParameters_NoModifiableFeatures() {

		var testData = new FluentAPIMethodTestData();
		testData.setMethodNamePrefix((eCls) -> String
				.format(FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodNameTemplate(), eCls.getName()));
		testData.seteClssToCheckFor(FluentAPIGenerationTestUtil.getAllSupportedConcreteEClssWithNoModifiableFeat());
		methodTestTemplate(testData);
	}

	/**
	 * Checks whether {@code newX(Y) : X} methods for all supported EClasses (with
	 * exactly one modifiable feature according to the metamodel feature filter)
	 * exist in API
	 */
	@Test
	public void methodTest_API_NewX_WithParameter_SingleModifiableFeature() {
		var testData = new FluentAPIMethodTestData();
		testData.setMethodNamePrefix((eCls) -> String
				.format(FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodNameTemplate(), eCls.getName()));
		testData.setReturnTypeOfOp(FluentAPIGenerationTestUtil.getElemEClsToInitEClsFunc());
		testData.setParamNames(
				(eCls) -> List.of(FluentAPIGeneralParameterGenerator.getFluentAPIFeatureValueParameterName()));
		testData.setParamTypes((eCls) -> List.of(eCls));
		testData.seteClssToCheckFor(
				FluentAPIGenerationTestUtil.getAllSupportedConcreteEClssWithOnlyOneModifiableFeat());
		testData.setExpectMultiValueVariants(FluentAPIGenerationTestUtil.getMultiValFunc());
		testData.setExpectBigNumberVariants(FluentAPIGenerationTestUtil.getBigNumberVariantsFunc());
		methodTestTemplate(testData);
	}

	/**
	 * Checks whether {@code modifyX(X) : XInitialisation} methods for all supported
	 * EClasses exist in API
	 */
	@Test
	public void methodTest_API_modifyX() {
		var testData = new FluentAPIMethodTestData();
		testData.setMethodNamePrefix((eCls) -> String
				.format(FluentAPIRootAPIConstants.getFluentAPIRootAPIModifyMethodNameTemplate(), eCls.getName()));
		testData.setReturnTypeOfOp(FluentAPIGenerationTestUtil.getElemEClsToInitEClsFunc());
		testData.setParamNames(
				(eCls) -> List.of(FluentAPIGeneralParameterGenerator.getFluentAPIEObjectParameterName()));
		testData.setParamTypes((eCls) -> List.of(eCls));
		testData.seteClssToCheckFor(FluentAPIGenerationTestUtil.getAllSupportedConcreteEClss());
		methodTestTemplate(testData);
	}

	/**
	 * Checks whether {@code modifyMarkedX(markKey) : XInitialisation} methods for
	 * all supported EClasses exist in API
	 */
	@Test
	public void methodTest_API_modifyMarkedX() {
		var testData = new FluentAPIMethodTestData();
		testData.setMethodNamePrefix((eCls) -> String
				.format(FluentAPIRootAPIConstants.getFluentAPIRootAPIModifyMarkedMethodNameTemplate(), eCls.getName()));
		testData.setReturnTypeOfOp(FluentAPIGenerationTestUtil.getElemEClsToInitEClsFunc());
		testData.setParamNames(
				(eCls) -> List.of(FluentAPIGeneralParameterGenerator.getFluentAPIMarkKeyParameterName()));
		testData.setParamTypes((eCls) -> List.of(EcorePackage.Literals.EJAVA_OBJECT));
		testData.seteClssToCheckFor(FluentAPIGenerationTestUtil.getAllSupportedConcreteEClss());
		methodTestTemplate(testData);
	}

	/**
	 * Checks whether {@code continueX(X) : XInitialisation} methods for all
	 * supported EClasses exist in API
	 */
	@Test
	public void methodTest_API_continueX() {
		var testData = new FluentAPIMethodTestData();
		testData.setMethodNamePrefix((eCls) -> String
				.format(FluentAPIRootAPIConstants.getFluentAPIRootAPIContinueMethodNameTemplate(), eCls.getName()));
		testData.setReturnTypeOfOp(FluentAPIGenerationTestUtil.getElemEClsToInitEClsFunc());
		testData.seteClssToCheckFor(FluentAPIGenerationTestUtil.getAllSupportedConcreteEClssWithModifiableFeats());
		methodTestTemplate(testData);
	}

	/**
	 * Checks whether {@code continueMarkedX(markKey) : XInitialisation} methods for
	 * all supported EClasses exist in API
	 */
	@Test
	public void methodTest_API_continueMarkedX() {
		var testData = new FluentAPIMethodTestData();
		testData.setMethodNamePrefix((eCls) -> String.format(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIContinueMarkedMethodNameTemplate(), eCls.getName()));
		testData.setReturnTypeOfOp(FluentAPIGenerationTestUtil.getElemEClsToInitEClsFunc());
		testData.setParamNames(
				(eCls) -> List.of(FluentAPIGeneralParameterGenerator.getFluentAPIMarkKeyParameterName()));
		testData.setParamTypes((eCls) -> List.of(EcorePackage.Literals.EJAVA_OBJECT));
		testData.seteClssToCheckFor(FluentAPIGenerationTestUtil.getAllSupportedConcreteEClssWithModifiableFeats());
		methodTestTemplate(testData);
	}

	/**
	 * Checks whether {@code getMarkedX(markKey) : X} methods for all supported
	 * EClasses exist in API
	 */
	@Test
	public void methodTest_API_getMarkedX() {
		var testData = new FluentAPIMethodTestData();
		testData.setMethodNamePrefix((eCls) -> String
				.format(FluentAPIRootAPIConstants.getFluentAPIRootAPIGetMarkedXMethodNameTemplate(), eCls.getName()));
		testData.setParamNames(
				(eCls) -> List.of(FluentAPIGeneralParameterGenerator.getFluentAPIMarkKeyParameterName()));
		testData.setParamTypes((eCls) -> List.of(EcorePackage.Literals.EJAVA_OBJECT));
		testData.seteClssToCheckFor(FluentAPIGenerationTestUtil.getAllSupportedConcreteEClss());
		methodTestTemplate(testData);
	}
}
