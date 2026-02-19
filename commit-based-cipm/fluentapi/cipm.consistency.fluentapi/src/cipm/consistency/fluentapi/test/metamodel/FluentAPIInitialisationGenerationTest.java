package cipm.consistency.fluentapi.test.metamodel;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EcorePackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.api.ApiFactory;
import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelFeatureFilter;
import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelPackageProvider;
import cipm.consistency.fluentapi.gen.init.FluentAPIInitialisationConstants;
import cipm.consistency.fluentapi.gen.metamodels.java.FluentAPIJavaMetamodelFeatureFilter;
import cipm.consistency.fluentapi.gen.metamodels.java.FluentAPIJavaMetamodelPackageProvider;
import cipm.consistency.fluentapi.test.AbstractFluentAPITest;

public class FluentAPIInitialisationGenerationTest extends AbstractFluentAPITest {
	private static final FluentAPITargetMetamodelFeatureFilter featureFilter = new FluentAPIJavaMetamodelFeatureFilter();
	private static final FluentAPITargetMetamodelPackageProvider metamodelProvider = new FluentAPIJavaMetamodelPackageProvider();

	private void methodTestTemplate(EClass initECls, String methodNamePrefix) {
		var allOpsWithMatchingMethodNamePrefix = initECls.getEOperations().stream()
				.filter((op) -> op.getName().startsWith(methodNamePrefix)).collect(Collectors.toList());
		for (var feature : featureFilter.getModifiableFeatures(initECls)) {
			var paramName = feature.getName();
			var paramType = feature.getEType();
			var expectMultiValueVariants = feature.isMany();
			var expectBigNumberVariants = feature.getEType().equals(EcorePackage.Literals.EBIG_INTEGER)
					|| feature.getEType().equals(EcorePackage.Literals.EBIG_DECIMAL);

			var currentEClssOpName = methodNamePrefix
					+ FluentAPIInitialisationConstants.getElementToInitialiseName(feature);
			var currentEClssOps = allOpsWithMatchingMethodNamePrefix.stream()
					.filter((op) -> op.getName().equals(currentEClssOpName))
//					.filter((op) -> op.getEType().equals(initECls))
					.collect(Collectors.toList());

			// Ensure that the original method is present
			Assertions.assertTrue(currentEClssOps.stream()
					.anyMatch((op) -> assertParamsEqual(op, List.of(paramName), List.of(paramType))));

			if (expectMultiValueVariants) {
				Assertions.assertEquals(3, currentEClssOps.size(), "For eCls " + initECls.getName());
				Assertions.assertTrue(currentEClssOps.stream()
						.anyMatch((op) -> assertArrayValuedParamsEqual(op, List.of(paramName), List.of(paramType))));
				Assertions.assertTrue(currentEClssOps.stream().anyMatch(
						(op) -> assertCollectionValuedParamsEqual(op, List.of(paramName), List.of(paramType))));
			} else if (expectBigNumberVariants) {
				Assertions.assertEquals(3, currentEClssOps.size(), "For eCls " + initECls.getName());
				Assertions.assertTrue(currentEClssOps.stream()
						.anyMatch((op) -> assertParamsEqual(op, List.of(paramName),
								List.of(paramType).stream().map(
										(t) -> t == EcorePackage.Literals.EBIG_INTEGER ? EcorePackage.Literals.EINT : t)
										.collect(Collectors.toList()))));
				Assertions.assertTrue(currentEClssOps.stream()
						.anyMatch((op) -> assertParamsEqual(op, List.of(paramName), List.of(paramType).stream()
								.map((t) -> t == EcorePackage.Literals.EBIG_INTEGER ? EcorePackage.Literals.ELONG : t)
								.collect(Collectors.toList()))));
				Assertions.assertTrue(currentEClssOps.stream()
						.anyMatch((op) -> assertParamsEqual(op, List.of(paramName), List.of(paramType).stream()
								.map((t) -> t == EcorePackage.Literals.EBIG_DECIMAL ? EcorePackage.Literals.EFLOAT : t)
								.collect(Collectors.toList()))));
				Assertions.assertTrue(currentEClssOps.stream()
						.anyMatch((op) -> assertParamsEqual(op, List.of(paramName), List.of(paramType).stream()
								.map((t) -> t == EcorePackage.Literals.EBIG_DECIMAL ? EcorePackage.Literals.EDOUBLE : t)
								.collect(Collectors.toList()))));
			} else {
				Assertions.assertEquals(1, currentEClssOps.size(), "For eCls " + initECls.getName());
				Assertions
						.assertTrue(assertParamsEqual(currentEClssOps.get(0), List.of(paramName), List.of(paramType)));
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
	 * Checks whether{@code withX() : XInitialisation} methods for all supported EClasses
	 * exist in API
	 */
	@Test
	public void methodTest_Initialisation_WithX() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var allConcreteEClss = metamodelProvider.getAllTargetMetamodelConcreteEClasses();
		for (var eCls : allConcreteEClss) {
			methodTestTemplate(api.getInitialisationForX(eCls).eClass(),
					String.format(FluentAPIInitialisationConstants.getFluentAPIInitialisationWithXFeatNameTemplate(), ""));
		}
	}
}
