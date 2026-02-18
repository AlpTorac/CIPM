package cipm.consistency.fluentapi.test;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EcorePackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.api.ApiFactory;
import cipm.consistency.fluentapi.gen.FluentAPIGeneralParameterGenerator;
import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelFeatureFilter;
import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelPackageProvider;
import cipm.consistency.fluentapi.gen.metamodels.java.FluentAPIJavaMetamodelFeatureFilter;
import cipm.consistency.fluentapi.gen.metamodels.java.FluentAPIJavaMetamodelPackageProvider;
import cipm.consistency.fluentapi.gen.rootapi.FluentAPIRootAPIConstants;

public class FluentAPIRootAPIGenerationTest extends AbstractFluentAPITest {
	private static final FluentAPITargetMetamodelFeatureFilter featureFilter = new FluentAPIJavaMetamodelFeatureFilter();
	private static final FluentAPITargetMetamodelPackageProvider metamodelProvider = new FluentAPIJavaMetamodelPackageProvider();
	private static final List<EOperation> allAPIOps = List
			.copyOf(ApiFactory.eINSTANCE.createFluentEObjectAPI().eClass().getEOperations());

	// TODO Add tests for other generated methods in API
	// TODO Include all generated EClasses

	// TODO Refactor this method
	private void methodTestTemplate(String methodNamePrefix, Function<EClass, EClassifier> returnTypeOfOpFunc,
			Function<EClass, List<String>> paramNamesFunc, Function<EClass, List<EClassifier>> paramTypesFunc,
			List<EClass> eClssToCheckFor, Function<EClass, Boolean> expectMultiValueVariantsFunc,
			Function<EClass, Boolean> expectBigNumberVariantsFunc) {

		var allOpsWithMatchingMethodNamePrefix = allAPIOps.stream()
				.filter((op) -> op.getName().startsWith(methodNamePrefix)).collect(Collectors.toList());
		for (var eCls : eClssToCheckFor) {
			var paramNames = paramNamesFunc.apply(eCls);
			var paramTypes = paramTypesFunc.apply(eCls);
			var expectMultiValueVariants = expectMultiValueVariantsFunc.apply(eCls);
			var expectBigNumberVariants = expectBigNumberVariantsFunc.apply(eCls);
			if (paramNames.size() != paramTypes.size())
				throw new IllegalArgumentException();

			var currentEClssOpName = methodNamePrefix + eCls.getName();
			var currentEClssOps = allOpsWithMatchingMethodNamePrefix.stream()
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
		var allConcreteEClss = metamodelProvider.getAllTargetMetamodelConcreteEClasses();
		methodTestTemplate(
				String.format(FluentAPIRootAPIConstants.getFluentAPIRootAPICreateNewXMethodNameTemplate(), ""),
				(eCls) -> eCls, (eCls) -> List.of(), (eCls) -> List.of(), allConcreteEClss, (eCls) -> false,
				(eCls) -> false);
	}

	/**
	 * Checks whether {@code newX() : XInitialisation} methods for all supported
	 * EClasses exist in API
	 */
	@Test
	public void methodTest_API_NewX_WithoutParameters() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var allConcreteEClss = metamodelProvider.getAllTargetMetamodelConcreteEClasses();
		methodTestTemplate(String.format(FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodNameTemplate(), ""),
				(eCls) -> api.getInitialisationForX(eCls).eClass(), (eCls) -> List.of(), (eCls) -> List.of(),
				allConcreteEClss, (eCls) -> false, (eCls) -> false);
	}

	/**
	 * Checks whether {@code newX() : X} methods for all supported EClasses (without
	 * any modifiable features according to the metamodel feature filter) exist in
	 * API
	 */
	@Test
	public void methodTest_API_NewX_WithoutParameters_NoModifiableFeatures() {
		var allConcreteEClss = metamodelProvider.getAllTargetMetamodelConcreteEClasses();
		methodTestTemplate(String.format(FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodNameTemplate(), ""),
				(eCls) -> eCls, (eCls) -> List.of(), (eCls) -> List.of(),
				allConcreteEClss.stream().filter(featureFilter::hasModifiableFeatures).collect(Collectors.toList()),
				(eCls) -> false, (eCls) -> false);
	}

	/**
	 * Checks whether {@code newX(Y) : X} methods for all supported EClasses (with
	 * exactly one modifiable feature according to the metamodel feature filter)
	 * exist in API
	 */
	@Test
	public void methodTest_API_NewX_WithParameter_SingleModifiableFeature() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var allConcreteEClss = metamodelProvider.getAllTargetMetamodelConcreteEClasses();
		methodTestTemplate(String.format(FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodNameTemplate(), ""),
				(eCls) -> api.getInitialisationForX(eCls).eClass(),
				(eCls) -> List.of(FluentAPIGeneralParameterGenerator.getFluentAPIFeatureValueParameterName()),
				(eCls) -> List.of(featureFilter.getModifiableFeatures(eCls).get(0).getEType()),
				allConcreteEClss.stream().filter((eCls) -> featureFilter.getModifiableFeatureCount(eCls) == 1)
						.collect(Collectors.toList()),
				(eCls) -> featureFilter.getModifiableFeatures(eCls).get(0).isMany(),
				(eCls) -> featureFilter.getModifiableFeatures(eCls).get(0).getEType()
						.equals(EcorePackage.Literals.EBIG_INTEGER)
						|| featureFilter.getModifiableFeatures(eCls).get(0).getEType()
								.equals(EcorePackage.Literals.EBIG_DECIMAL));
	}

	/**
	 * Checks whether {@code modifyX(X) : XInitialisation} methods for all supported
	 * EClasses exist in API
	 */
	@Test
	public void methodTest_API_modifyX() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var allConcreteEClss = metamodelProvider.getAllTargetMetamodelConcreteEClasses();
		methodTestTemplate(String.format(FluentAPIRootAPIConstants.getFluentAPIRootAPIModifyMethodNameTemplate(), ""),
				(eCls) -> api.getInitialisationForX(eCls).eClass(),
				(eCls) -> List.of(FluentAPIGeneralParameterGenerator.getFluentAPIEObjectParameterName()),
				(eCls) -> List.of(eCls), allConcreteEClss, (eCls) -> false, (eCls) -> false);
	}

	/**
	 * Checks whether {@code modifyMarkedX(markKey) : XInitialisation} methods for
	 * all supported EClasses exist in API
	 */
	@Test
	public void methodTest_API_modifyMarkedX() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var allConcreteEClss = metamodelProvider.getAllTargetMetamodelConcreteEClasses();
		methodTestTemplate(
				String.format(FluentAPIRootAPIConstants.getFluentAPIRootAPIModifyMarkedMethodNameTemplate(), ""),
				(eCls) -> api.getInitialisationForX(eCls).eClass(),
				(eCls) -> List.of(FluentAPIGeneralParameterGenerator.getFluentAPIMarkKeyParameterName()),
				(eCls) -> List.of(EcorePackage.Literals.EJAVA_OBJECT), allConcreteEClss, (eCls) -> false,
				(eCls) -> false);
	}

	/**
	 * Checks whether {@code continueX(X) : XInitialisation} methods for all
	 * supported EClasses exist in API
	 */
	@Test
	public void methodTest_API_continueX() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var allConcreteEClss = metamodelProvider.getAllTargetMetamodelConcreteEClasses();
		methodTestTemplate(String.format(FluentAPIRootAPIConstants.getFluentAPIRootAPIContinueMethodNameTemplate(), ""),
				(eCls) -> api.getInitialisationForX(eCls).eClass(), (eCls) -> List.of(), (eCls) -> List.of(),
				allConcreteEClss.stream().filter(featureFilter::hasModifiableFeatures).collect(Collectors.toList()),
				(eCls) -> false, (eCls) -> false);
	}

	/**
	 * Checks whether {@code continueMarkedX(markKey) : XInitialisation} methods for
	 * all supported EClasses exist in API
	 */
	@Test
	public void methodTest_API_continueMarkedX() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var allConcreteEClss = metamodelProvider.getAllTargetMetamodelConcreteEClasses();
		methodTestTemplate(
				String.format(FluentAPIRootAPIConstants.getFluentAPIRootAPIContinueMarkedMethodNameTemplate(), ""),
				(eCls) -> api.getInitialisationForX(eCls).eClass(),
				(eCls) -> List.of(FluentAPIGeneralParameterGenerator.getFluentAPIMarkKeyParameterName()),
				(eCls) -> List.of(EcorePackage.Literals.EJAVA_OBJECT),
				allConcreteEClss.stream().filter(featureFilter::hasModifiableFeatures).collect(Collectors.toList()),
				(eCls) -> false, (eCls) -> false);
	}

	/**
	 * Checks whether {@code getMarkedX(markKey) : X} methods for all supported
	 * EClasses exist in API
	 */
	@Test
	public void methodTest_API_getMarkedX() {
		var allConcreteEClss = metamodelProvider.getAllTargetMetamodelConcreteEClasses();

		methodTestTemplate(
				String.format(FluentAPIRootAPIConstants.getFluentAPIRootAPIGetMarkedXMethodNameTemplate(), ""),
				(eCls) -> eCls,
				(eCls) -> List.of(FluentAPIGeneralParameterGenerator.getFluentAPIMarkKeyParameterName()),
				(eCls) -> List.of(EcorePackage.Literals.EJAVA_OBJECT), allConcreteEClss, (eCls) -> false,
				(eCls) -> false);
	}
}
