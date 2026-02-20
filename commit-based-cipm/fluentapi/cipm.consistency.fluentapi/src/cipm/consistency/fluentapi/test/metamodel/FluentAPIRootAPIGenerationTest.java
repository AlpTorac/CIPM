package cipm.consistency.fluentapi.test.metamodel;

import java.util.List;
import java.util.stream.Collectors;

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
		FluentAPIGenerationTestSettings.setAPI(api);
		FluentAPIGenerationTestSettings.setElemEClsToInitEClsFunc((eCls) -> api.getInitialisationForX(eCls).eClass());
		FluentAPIGenerationTestSettings.setFeatureFilter(new FluentAPIJavaMetamodelFeatureFilter());
		FluentAPIGenerationTestSettings.setPackageProvider(new FluentAPIJavaMetamodelPackageProvider());
	}

	private void methodTestTemplate(FluentAPIMethodTestData testData) {
		Assertions.assertFalse(testData.geteClssToCheckFor().isEmpty());

		for (var eCls : testData.geteClssToCheckFor()) {
			var paramNames = testData.getParamNames(eCls);
			var paramTypes = testData.getParamTypes(eCls);
			var expectMultiValueVariants = testData.getExpectMultiValueVariants(eCls);
			var expectBigNumberVariants = testData.getExpectBigNumberVariants(eCls);
			if (paramNames.size() != paramTypes.size())
				throw new IllegalArgumentException();

			var currentEClssOpName = testData.getMethodName(eCls);
			var currentEClssOps = FluentAPIGenerationTestSettings.getAllAPIOps().stream()
					.filter((op) -> op.getName().equals(currentEClssOpName))
					.filter((op) -> op.getEParameters().size() == paramNames.size())
//					.filter((op) -> op.getEType().equals(returnTypeOfOpFunc.apply(eCls))
					.collect(Collectors.toList());

			// Ensure that the original method is present
			FluentAPIGenerationTestAssertions.assertOriginalMethodExists(eCls, currentEClssOps, paramNames, paramTypes);

			if (expectMultiValueVariants) {
				FluentAPIGenerationTestAssertions.assertMultiValueVariantsExist(eCls, currentEClssOps, paramNames,
						paramTypes);
			}
			if (expectBigNumberVariants) {
				FluentAPIGenerationTestAssertions.assertBigNumberVariantsExist(eCls, currentEClssOps, paramNames,
						paramTypes);
			}
		}
	}

	/**
	 * Checks whether{@code createNewX() : X} methods for all supported EClasses
	 * exist in API
	 */
	@Test
	public void methodTest_API_CreateNewX() {

		var testData = new FluentAPIMethodTestData();
		testData.seteClssToCheckFor(FluentAPIGenerationTestSettings.getAllSupportedConcreteEClss());
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
		testData.setReturnTypeOfOp(FluentAPIGenerationTestSettings.getElemEClsToInitEClsFunc());
		testData.seteClssToCheckFor(FluentAPIGenerationTestSettings.getAllSupportedConcreteEClss());
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
		testData.seteClssToCheckFor(FluentAPIGenerationTestSettings.getAllSupportedConcreteEClssWithNoModifiableFeat());
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
		testData.setReturnTypeOfOp(FluentAPIGenerationTestSettings.getElemEClsToInitEClsFunc());
		testData.setParamNames(
				(eCls) -> List.of(FluentAPIGeneralParameterGenerator.getFluentAPIFeatureValueParameterName()));
		testData.setParamTypes((eCls) -> List.of(eCls));
		testData.seteClssToCheckFor(
				FluentAPIGenerationTestSettings.getAllSupportedConcreteEClssWithOnlyOneModifiableFeat());
		testData.setExpectMultiValueVariants(FluentAPIGenerationTestSettings.getMultiValFunc());
		testData.setExpectBigNumberVariants(FluentAPIGenerationTestSettings.getBigNumberVariantsFunc());
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
		testData.setReturnTypeOfOp(FluentAPIGenerationTestSettings.getElemEClsToInitEClsFunc());
		testData.setParamNames(
				(eCls) -> List.of(FluentAPIGeneralParameterGenerator.getFluentAPIEObjectParameterName()));
		testData.setParamTypes((eCls) -> List.of(eCls));
		testData.seteClssToCheckFor(FluentAPIGenerationTestSettings.getAllSupportedConcreteEClss());
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
		testData.setReturnTypeOfOp(FluentAPIGenerationTestSettings.getElemEClsToInitEClsFunc());
		testData.setParamNames(
				(eCls) -> List.of(FluentAPIGeneralParameterGenerator.getFluentAPIMarkKeyParameterName()));
		testData.setParamTypes((eCls) -> List.of(EcorePackage.Literals.EJAVA_OBJECT));
		testData.seteClssToCheckFor(FluentAPIGenerationTestSettings.getAllSupportedConcreteEClss());
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
		testData.setReturnTypeOfOp(FluentAPIGenerationTestSettings.getElemEClsToInitEClsFunc());
		testData.seteClssToCheckFor(FluentAPIGenerationTestSettings.getAllSupportedConcreteEClssWithModifiableFeats());
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
		testData.setReturnTypeOfOp(FluentAPIGenerationTestSettings.getElemEClsToInitEClsFunc());
		testData.setParamNames(
				(eCls) -> List.of(FluentAPIGeneralParameterGenerator.getFluentAPIMarkKeyParameterName()));
		testData.setParamTypes((eCls) -> List.of(EcorePackage.Literals.EJAVA_OBJECT));
		testData.seteClssToCheckFor(FluentAPIGenerationTestSettings.getAllSupportedConcreteEClssWithModifiableFeats());
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
		testData.seteClssToCheckFor(FluentAPIGenerationTestSettings.getAllSupportedConcreteEClss());
		methodTestTemplate(testData);
	}
}
