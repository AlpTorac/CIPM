package cipm.consistency.fluentapi.test.metamodel;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EcorePackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fluentapi.test.AbstractFluentAPITest;
import cipm.consistency.fluentapi.gen.ModelConstants;

public abstract class AbstractFluentAPIRootAPIGenerationTest extends AbstractFluentAPITest
		implements IFluentAPIMetamodelTest, IFluentAPIMutationTest {

	@Test
	public void mutationTest(TestInfo info) {
		var api = getAPI();
		var eClssToMutate = new FluentAPIMutationTestRepresentativesGenerator()
				.getRepresentativeTargetMetamodelConcreteEClasses_BasedOnModifiability();

		var eClssToMutateNames = eClssToMutate.stream().map((eCls) -> eCls.getName()).collect(Collectors.toList());

		// Remove EOperations for certain types
		var opsToRemove = api.eClass().getEOperations().stream()
				.filter((op) -> eClssToMutateNames.stream().anyMatch((eClsName) -> op.getName().endsWith(eClsName)))
				.collect(Collectors.toList());

		// Mutate the existing EMF model by removing the methods from above
		var oldOps = List.copyOf(api.eClass().getEOperations());
		api.eClass().getEOperations().removeAll(opsToRemove);
		FluentAPIGenerationTestSettings.setAPI(api);

		var mutTestRes = performMutationTesting(info);

		// Revert the mutation to EMF model
		api.eClass().getEOperations().clear();
		api.eClass().getEOperations().addAll(oldOps);
		FluentAPIGenerationTestSettings.setAPI(api);

		assertTestsFailed(mutTestRes);
	}

	@BeforeEach
	public void setUp() {
		super.setUp();
		FluentAPIGenerationTestSettings.setAPI(getAPI());
		FluentAPIGenerationTestSettings.setElemEClsToInitEClsFunc((eCls) -> api_getInitialisationForX(eCls).eClass());
		FluentAPIGenerationTestSettings.setFeatureFilter(getFilter());
		FluentAPIGenerationTestSettings.setPackageProvider(getProvider());
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
		testData.setMethodNamePrefix((eCls) -> ModelConstants.FluentAPI.CreateNew.NAME.getFor(eCls.getName()));
		methodTestTemplate(testData);
	}

	/**
	 * Checks whether {@code newX() : XInitialisation} methods for all supported
	 * EClasses exist in API
	 */
	@Test
	public void methodTest_API_NewX_WithoutParameters() {

		var testData = new FluentAPIMethodTestData();
		testData.setMethodNamePrefix((eCls) -> ModelConstants.FluentAPI.New.NAME.getFor(eCls.getName()));
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
		testData.setMethodNamePrefix((eCls) -> ModelConstants.FluentAPI.New.NAME.getFor(eCls.getName()));
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
		testData.setMethodNamePrefix((eCls) -> ModelConstants.FluentAPI.New.NAME.getFor(eCls.getName()));
		testData.setReturnTypeOfOp(FluentAPIGenerationTestSettings.getElemEClsToInitEClsFunc());
		testData.setParamNames((eCls) -> List.of(ModelConstants.GeneralParameters.FEATURE_VALUE_PARAMETER_NAME.get()));
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
		testData.setMethodNamePrefix((eCls) -> ModelConstants.FluentAPI.Modify.NAME.getFor(eCls.getName()));
		testData.setReturnTypeOfOp(FluentAPIGenerationTestSettings.getElemEClsToInitEClsFunc());
		testData.setParamNames((eCls) -> List.of(ModelConstants.GeneralParameters.USED_EOBJECT_PARAMETER_NAME.get()));
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
		testData.setMethodNamePrefix((eCls) -> ModelConstants.FluentAPI.ModifyMarked.NAME.getFor(eCls.getName()));
		testData.setReturnTypeOfOp(FluentAPIGenerationTestSettings.getElemEClsToInitEClsFunc());
		testData.setParamNames((eCls) -> List.of(ModelConstants.GeneralParameters.MARK_KEY_PARAMETER_NAME.get()));
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
		testData.setMethodNamePrefix((eCls) -> ModelConstants.FluentAPI.Continue.NAME.getFor(eCls.getName()));
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
		testData.setMethodNamePrefix((eCls) -> ModelConstants.FluentAPI.ContinueMarked.NAME.getFor(eCls.getName()));
		testData.setReturnTypeOfOp(FluentAPIGenerationTestSettings.getElemEClsToInitEClsFunc());
		testData.setParamNames((eCls) -> List.of(ModelConstants.GeneralParameters.MARK_KEY_PARAMETER_NAME.get()));
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
		testData.setMethodNamePrefix((eCls) -> ModelConstants.FluentAPI.GetMarked.NAME.getFor(eCls.getName()));
		testData.setParamNames((eCls) -> List.of(ModelConstants.GeneralParameters.MARK_KEY_PARAMETER_NAME.get()));
		testData.setParamTypes((eCls) -> List.of(EcorePackage.Literals.EJAVA_OBJECT));
		testData.seteClssToCheckFor(FluentAPIGenerationTestSettings.getAllSupportedConcreteEClss());
		methodTestTemplate(testData);
	}
}
