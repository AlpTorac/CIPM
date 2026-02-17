package cipm.consistency.fluentapi.test;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
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

public class FluentAPIGenerationTest extends AbstractFluentAPITest {
	private static final FluentAPITargetMetamodelFeatureFilter featureFilter = new FluentAPIJavaMetamodelFeatureFilter();
	private static final FluentAPITargetMetamodelPackageProvider metamodelProvider = new FluentAPIJavaMetamodelPackageProvider();

	// TODO Add tests for other generated methods in API
	// TODO Include all generated EClasses

	private void methodTestTemplate(String methodNamePrefix, Function<EClass, EClassifier> returnTypeOfOpFunc,
			Function<EClass, List<String>> paramNamesFunc, Function<EClass, List<EClassifier>> paramTypesFunc,
			List<EClass> eClssToCheckFor) {

		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var allOpsWithMatchingMethodNamePrefix = api.eClass().getEOperations().stream()
				.filter((op) -> op.getName().startsWith(methodNamePrefix)).collect(Collectors.toList());
		for (var eCls : eClssToCheckFor) {
			var paramNames = paramNamesFunc.apply(eCls);
			var paramTypes = paramTypesFunc.apply(eCls);
			if (paramNames.size() != paramTypes.size())
				throw new IllegalArgumentException();

			var currentEClssOpName = methodNamePrefix + eCls.getName();
			var currentEClssOps = allOpsWithMatchingMethodNamePrefix.stream()
					.filter((op) -> op.getName().equals(currentEClssOpName))
					.filter((op) -> op.getEParameters().size() == paramNames.size())
//					.filter((op) -> op.getEType().equals(returnTypeOfOpFunc.apply(eCls))
					.collect(Collectors.toList());
			Assertions.assertEquals(1, currentEClssOps.size(), "For eCls " + eCls.getName());
			var currentEClssOp = currentEClssOps.get(0);
			for (int i = 0; i < paramNames.size(); i++) {
				var currentParam = currentEClssOp.getEParameters().get(i);
				Assertions.assertEquals(paramNames.get(i), currentParam.getName());
				// TODO Find out if it is possible to type check, currently the return type of
				// the op is a proxy object
//				Assertions.assertEquals(paramTypes.get(i), currentParam.getEType());
			}
		}
	}

	@Test
	public void methodTest_API_CreateNewX() {
		var allConcreteEClss = metamodelProvider.getAllTargetMetamodelConcreteEClasses();
		methodTestTemplate(
				String.format(FluentAPIRootAPIConstants.getFluentAPIRootAPICreateNewXMethodNameTemplate(), ""),
				(eCls) -> eCls, (eCls) -> List.of(), (eCls) -> List.of(), allConcreteEClss);
	}

	@Test
	public void methodTest_API_NewX_WithoutParameters() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var allConcreteEClss = metamodelProvider.getAllTargetMetamodelConcreteEClasses();
		methodTestTemplate(String.format(FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodNameTemplate(), ""),
				(eCls) -> api.getInitialisationForX(eCls).eClass(), (eCls) -> List.of(), (eCls) -> List.of(),
				allConcreteEClss);
	}

	@Test
	public void methodTest_API_modifyX() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var allConcreteEClss = metamodelProvider.getAllTargetMetamodelConcreteEClasses();
		methodTestTemplate(String.format(FluentAPIRootAPIConstants.getFluentAPIRootAPIModifyMethodNameTemplate(), ""),
				(eCls) -> api.getInitialisationForX(eCls).eClass(),
				(eCls) -> List.of(FluentAPIGeneralParameterGenerator.getFluentAPIEObjectParameterName()),
				(eCls) -> List.of(eCls), allConcreteEClss);
	}

	@Test
	public void methodTest_API_modifyMarkedX() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var allConcreteEClss = metamodelProvider.getAllTargetMetamodelConcreteEClasses();
		methodTestTemplate(
				String.format(FluentAPIRootAPIConstants.getFluentAPIRootAPIModifyMarkedMethodNameTemplate(), ""),
				(eCls) -> api.getInitialisationForX(eCls).eClass(),
				(eCls) -> List.of(FluentAPIGeneralParameterGenerator.getFluentAPIMarkKeyParameterName()),
				(eCls) -> List.of(EcorePackage.Literals.EJAVA_OBJECT), allConcreteEClss);
	}

	@Test
	public void methodTest_API_continueX() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var allConcreteEClss = metamodelProvider.getAllTargetMetamodelConcreteEClasses();
		methodTestTemplate(String.format(FluentAPIRootAPIConstants.getFluentAPIRootAPIContinueMethodNameTemplate(), ""),
				(eCls) -> api.getInitialisationForX(eCls).eClass(), (eCls) -> List.of(), (eCls) -> List.of(),
				allConcreteEClss.stream().filter(featureFilter::hasModifiableFeatures).collect(Collectors.toList()));
	}

	@Test
	public void methodTest_API_continueMarkedX() {
		var api = ApiFactory.eINSTANCE.createFluentEObjectAPI();
		var allConcreteEClss = metamodelProvider.getAllTargetMetamodelConcreteEClasses();
		methodTestTemplate(
				String.format(FluentAPIRootAPIConstants.getFluentAPIRootAPIContinueMarkedMethodNameTemplate(), ""),
				(eCls) -> api.getInitialisationForX(eCls).eClass(),
				(eCls) -> List.of(FluentAPIGeneralParameterGenerator.getFluentAPIMarkKeyParameterName()),
				(eCls) -> List.of(EcorePackage.Literals.EJAVA_OBJECT),
				allConcreteEClss.stream().filter(featureFilter::hasModifiableFeatures).collect(Collectors.toList()));
	}

	@Test
	public void methodTest_API_getMarkedX() {
		var allConcreteEClss = metamodelProvider.getAllTargetMetamodelConcreteEClasses();

		methodTestTemplate(
				String.format(FluentAPIRootAPIConstants.getFluentAPIRootAPIGetMarkedXMethodNameTemplate(), ""),
				(eCls) -> eCls,
				(eCls) -> List.of(FluentAPIGeneralParameterGenerator.getFluentAPIMarkKeyParameterName()),
				(eCls) -> List.of(EcorePackage.Literals.EJAVA_OBJECT), allConcreteEClss);
	}
}
