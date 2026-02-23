package cipm.consistency.fluentapi.test.metamodel;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fluentapi.api.ApiFactory;
import cipm.consistency.fluentapi.gen.init.FluentAPIInitialisationConstants;
import cipm.consistency.fluentapi.gen.metamodels.java.FluentAPIJavaMetamodelFeatureFilter;
import cipm.consistency.fluentapi.gen.metamodels.java.FluentAPIJavaMetamodelPackageProvider;
import cipm.consistency.fluentapi.test.AbstractFluentAPITest;

public class FluentAPIInitialisationGenerationTest extends AbstractFluentAPITest {
	@Test
	public void mutationTest(TestInfo info) {
		var eClssToMutate = new FluentAPIMutationTestRepresentativesGenerator()
				.getRepresentativeTargetMetamodelConcreteEClasses_BasedOnModifiability().stream()
				.map((eCls) -> FluentAPIGenerationTestSettings.getElemEClsToInitEClsFunc().apply(eCls))
				.collect(Collectors.toSet());

		// Remove EOperations for certain types
		var opsToRemove = new LinkedHashMap<EClass, List<EOperation>>();
		eClssToMutate.stream().forEach((eCls) -> opsToRemove.put(eCls, new ArrayList<>()));
		eClssToMutate.stream().forEach((eCls) -> eCls.getEOperations().stream()
				.filter((op) -> op.getName().startsWith(String.format(
						FluentAPIInitialisationConstants.getFluentAPIInitialisationWithXFeatNameTemplate(), "")))
				.forEach((op) -> opsToRemove.get(eCls).add(op)));
		eClssToMutate.stream().forEach((eCls) -> eCls.getEOperations().stream()
				.filter((op) -> op.getName().startsWith(String.format(
						FluentAPIInitialisationConstants.getFluentAPIInitialisationWithoutXFeatNameTemplate(), "")))
				.forEach((op) -> opsToRemove.get(eCls).add(op)));
		eClssToMutate.stream().forEach((eCls) -> eCls.getEOperations().stream()
				.filter((op) -> op.getName().startsWith(String.format(
						FluentAPIInitialisationConstants.getFluentAPIInitialisationWithAddedXFeatNameTemplate(), "")))
				.forEach((op) -> opsToRemove.get(eCls).add(op)));
		eClssToMutate.stream()
				.forEach((eCls) -> eCls.getEOperations().stream()
						.filter((op) -> op.getName()
								.startsWith(String.format(FluentAPIInitialisationConstants
										.getFluentAPIInitialisationWithRemovedXFeatNameTemplate(), "")))
						.forEach((op) -> opsToRemove.get(eCls).add(op)));
		eClssToMutate.stream().forEach((eCls) -> eCls.getEOperations().stream()
				.filter((op) -> op.getName().startsWith(String.format(
						FluentAPIInitialisationConstants.getFluentAPIInitialisationCleanXFeatNameTemplate(), "")))
				.forEach((op) -> opsToRemove.get(eCls).add(op)));

		var oldOps = new LinkedHashMap<EClass, List<EOperation>>();
		eClssToMutate.stream().forEach((eCls) -> oldOps.put(eCls, List.copyOf(eCls.getEOperations())));

		opsToRemove.forEach((eCls, toRemove) -> eCls.getEOperations().removeAll(toRemove));

		var testMethodsToRun = List.of(this.getClass().getDeclaredMethods()).stream()
				.filter((tm) -> !tm.getName().equals(info.getDisplayName()))
				.filter((tm) -> tm.isAnnotationPresent(org.junit.jupiter.api.Test.class)).collect(Collectors.toList());
		Assertions.assertTrue(testMethodsToRun.size() > 0, "No test methods detected");
		testMethodsToRun.forEach((tm) -> Assertions.assertThrows(Exception.class, () -> tm.invoke(this)));

		oldOps.forEach((eCls, ops) -> {
			eCls.getEOperations().clear();
			eCls.getEOperations().addAll(ops);
		});
	}

	@BeforeAll
	public static void setUpBeforeAll() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		FluentAPIGenerationTestSettings.setAPI(api);
		FluentAPIGenerationTestSettings.setElemEClsToInitEClsFunc((eCls) -> api.getInitialisationForX(eCls).eClass());
		FluentAPIGenerationTestSettings.setFeatureFilter(new FluentAPIJavaMetamodelFeatureFilter());
		FluentAPIGenerationTestSettings.setPackageProvider(new FluentAPIJavaMetamodelPackageProvider());
	}

	private void methodTestTemplate(EClass elemToInitECls, EClass initECls, String methodNamePrefix,
			String expectedParamName, List<EStructuralFeature> expectedFeats) {
		for (var feature : expectedFeats) {
			var paramName = expectedParamName;
			var hasParams = paramName != null;
			var paramType = feature.getEType();
			var expectMultiValueVariants = feature.isMany();
			var expectBigNumberVariants = FluentAPIGenerationTestSettings.getBigNumberVariantsFunc()
					.apply(elemToInitECls);

			var currentMetName = methodNamePrefix
					+ FluentAPIInitialisationConstants.getElementToInitialiseName(feature);
			var currentEClssOps = initECls.getEOperations().stream().filter((op) -> op.getName().equals(currentMetName))
//					.filter((op) -> op.getEType().equals(initECls))
					.collect(Collectors.toList());

			var paramNames = hasParams ? List.of(paramName) : List.<String>of();
			var paramTypes = hasParams ? List.of(paramType) : List.<EClassifier>of();

			// Ensure that the original method is present
			FluentAPIGenerationTestAssertions.assertOriginalMethodExists(initECls, currentEClssOps, paramNames,
					paramTypes);
			if (hasParams && expectMultiValueVariants) {
				FluentAPIGenerationTestAssertions.assertMultiValueVariantsExist(initECls, currentEClssOps, paramNames,
						paramTypes);
			}
			if (hasParams && expectBigNumberVariants) {
				FluentAPIGenerationTestAssertions.assertBigNumberVariantsExist(initECls, currentEClssOps, paramNames,
						paramTypes);
			}
		}
	}

	/**
	 * Checks whether{@code withX(featVal) : XInitialisation} methods for all
	 * supported EClasses exist in API
	 */
	@Test
	public void methodTest_Initialisation_WithX() {
		for (var eCls : FluentAPIGenerationTestSettings.getAllSupportedConcreteEClss()) {
			methodTestTemplate(eCls, FluentAPIGenerationTestSettings.getElemEClsToInitEClsFunc().apply(eCls),
					String.format(FluentAPIInitialisationConstants.getFluentAPIInitialisationWithXFeatNameTemplate(),
							""),
					FluentAPIInitialisationConstants.getFluentAPIInitialisationWithMethodNewFeatValParamName(),
					FluentAPIGenerationTestSettings.getFeatureFilter().getModifiableFeatures(eCls).stream()
							.filter((f) -> !f.isMany()).collect(Collectors.toList()));
		}
	}

	/**
	 * Checks whether{@code withoutX() : XInitialisation} methods for all supported
	 * EClasses exist in API
	 */
	@Test
	public void methodTest_Initialisation_WithoutX() {
		for (var eCls : FluentAPIGenerationTestSettings.getAllSupportedConcreteEClss()) {
			methodTestTemplate(eCls, FluentAPIGenerationTestSettings.getElemEClsToInitEClsFunc().apply(eCls),
					String.format(FluentAPIInitialisationConstants.getFluentAPIInitialisationWithoutXFeatNameTemplate(),
							""),
					null, FluentAPIGenerationTestSettings.getFeatureFilter().getModifiableFeatures(eCls).stream()
							.filter((f) -> !f.isMany()).collect(Collectors.toList()));
		}
	}

	/**
	 * Checks whether{@code withAddedX(featVals) : XInitialisation} methods for all
	 * supported EClasses exist in API
	 */
	@Test
	public void methodTest_Initialisation_WithAddedX() {
		for (var eCls : FluentAPIGenerationTestSettings.getAllSupportedConcreteEClss()) {
			methodTestTemplate(eCls, FluentAPIGenerationTestSettings.getElemEClsToInitEClsFunc().apply(eCls),
					String.format(
							FluentAPIInitialisationConstants.getFluentAPIInitialisationWithAddedXFeatNameTemplate(),
							""),
					FluentAPIInitialisationConstants.getFluentAPIInitialisationWithMethodAddedFeatValParamName(),
					FluentAPIGenerationTestSettings.getFeatureFilter().getModifiableFeatures(eCls).stream()
							.filter((f) -> f.isMany()).collect(Collectors.toList()));
		}
	}

	/**
	 * Checks whether{@code withRemovedX(featVals) : XInitialisation} methods for
	 * all supported EClasses exist in API
	 */
	@Test
	public void methodTest_Initialisation_WithRemovedX() {
		for (var eCls : FluentAPIGenerationTestSettings.getAllSupportedConcreteEClss()) {
			methodTestTemplate(eCls, FluentAPIGenerationTestSettings.getElemEClsToInitEClsFunc().apply(eCls),
					String.format(
							FluentAPIInitialisationConstants.getFluentAPIInitialisationWithRemovedXFeatNameTemplate(),
							""),
					FluentAPIInitialisationConstants.getFluentAPIInitialisationWithMethodRemovedFeatValParamName(),
					FluentAPIGenerationTestSettings.getFeatureFilter().getModifiableFeatures(eCls).stream()
							.filter((f) -> f.isMany()).collect(Collectors.toList()));
		}
	}

	/**
	 * Checks whether{@code cleanX() : XInitialisation} methods for all supported
	 * EClasses exist in API
	 */
	@Test
	public void methodTest_Initialisation_CleanX() {
		for (var eCls : FluentAPIGenerationTestSettings.getAllSupportedConcreteEClss()) {
			methodTestTemplate(eCls, FluentAPIGenerationTestSettings.getElemEClsToInitEClsFunc().apply(eCls),
					String.format(FluentAPIInitialisationConstants.getFluentAPIInitialisationCleanXFeatNameTemplate(),
							""),
					null, FluentAPIGenerationTestSettings.getFeatureFilter().getModifiableFeatures(eCls).stream()
							.filter((f) -> f.isMany()).collect(Collectors.toList()));
		}
	}
}
