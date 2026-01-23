package cipm.consistency.fluentapi.gen.init;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcoreFactory;

import cipm.consistency.fluentapi.gen.FluentAPIConstants;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelFeatureFilter;
import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelPackageProvider;
import cipm.consistency.fluentapi.gen.superinit.FluentAPISuperInitialisationCreateNowMethodGenerator;
import cipm.consistency.fluentapi.gen.superinit.FluentAPISuperInitialisationDropOperationGenerator;
import cipm.consistency.fluentapi.gen.superinit.FluentAPISuperInitialisationGetInitialisedEClassMethodGenerator;
import cipm.consistency.fluentapi.gen.superinit.FluentAPISuperInitialisationMarkMethodGenerator;
import cipm.consistency.fluentapi.gen.superinit.FluentAPISuperInitialisationNextInitialisationMethodGenerator;
import cipm.consistency.fluentapi.gen.superinit.FluentAPISuperInitialisationPreviousInitialisationMethodGenerator;
import cipm.consistency.fluentapi.gen.superinit.FluentAPISuperInitialisationResetOperationGenerator;
import cipm.consistency.fluentapi.gen.superinit.FluentAPISuperInitialisationToAPIMethodGenerator;

public class FluentAPIInitialisationEClassGenerator {
	// %s: Initialised class name
	// %s: Metamodel name
	// %s: Initialised class name
	// %s: Serialised method names and summaries
	private static final String initClassDocTemplate = "An Initialisation class that targets the type '%s' within the '%s' metamodel. Contains various methods that facilitate the programmatic construction of '%s' instances."
			+ FluentAPIGenerationUtil.getClassMethodOverviewIntroTemplate();

	private static final Map<String, String> summaries = new LinkedHashMap<>();

	public List<EClass> generateFluentAPIInitialisationClasses(
			FluentAPITargetMetamodelPackageProvider targetMetamodelPackageProvider,
			FluentAPITargetMetamodelFeatureFilter filter) {
		var allPackages = targetMetamodelPackageProvider.getTargetMetamodelPackages();
		var initSubClss = new ArrayList<EClass>();

		for (var pac : allPackages) {
			for (var initialisedEClass : pac.getEClassifiers().stream().filter((c) -> c instanceof EClass)
					.map((c) -> (EClass) c).filter(FluentAPIGenerationUtil::isConcrete)
					.collect(Collectors.toCollection(ArrayList::new))) {
				var initSubCls = generateInitialisationEClass(initialisedEClass, targetMetamodelPackageProvider);
				setupFluentAPIInitialisationFor(initSubCls, initialisedEClass, targetMetamodelPackageProvider, filter);
				initSubClss.add(initSubCls);
			}
		}

		return initSubClss;
	}

	private void addXInitEClassDocumentation(EClass xInitEClass, EClass initialisedEClass,
			FluentAPITargetMetamodelPackageProvider targetMetamodelPackageProvider) {
		var anno = EcoreFactory.eINSTANCE.createEAnnotation();
		anno.setSource(FluentAPIConstants.getGenModelURL());
		var doc = String.format(initClassDocTemplate, initialisedEClass.getName(),
				targetMetamodelPackageProvider.getTargetMetamodelName(), initialisedEClass.getName(),
				FluentAPIGenerationUtil.serialiseSummaries(summaries));
		anno.getDetails().put(FluentAPIGenerationUtil.getEOperationDocumentationKey(), doc);

		xInitEClass.getEAnnotations().add(anno);
	}

	private EClass generateInitialisationEClass(EClass initialisedEClass,
			FluentAPITargetMetamodelPackageProvider targetMetamodelPackageProvider) {
		var xInitEClass = EcoreFactory.eINSTANCE.createEClass();
		xInitEClass.setName(initialisedEClass.getName()
				+ FluentAPIInitialisationConstants.getFluentAPIInitialisationClassNameSuffix());
		return xInitEClass;
	}

	private void setupFluentAPIInitialisationFor(EClass xInitEClass, EClass initialisedEClass,
			FluentAPITargetMetamodelPackageProvider targetMetamodelPackageProvider,
			FluentAPITargetMetamodelFeatureFilter filter) {
		addNonOverriddenInheritedMethodSummaries();
		addOverridingOperations(xInitEClass, initialisedEClass);
		addOperations(xInitEClass, initialisedEClass, targetMetamodelPackageProvider, filter);
		addXInitEClassDocumentation(xInitEClass, initialisedEClass, targetMetamodelPackageProvider);
	}

	private void addNonOverriddenInheritedMethodSummaries() {
		var getInitEClsGen = new FluentAPISuperInitialisationGetInitialisedEClassMethodGenerator();
		summaries.putAll(getInitEClsGen.getMethodNamesToDescriptions());

		var dropGen = new FluentAPISuperInitialisationDropOperationGenerator();
		summaries.putAll(dropGen.getMethodNamesToDescriptions());

		var resetGen = new FluentAPISuperInitialisationResetOperationGenerator();
		summaries.putAll(resetGen.getMethodNamesToDescriptions());

		var toAPIGen = new FluentAPISuperInitialisationToAPIMethodGenerator();
		summaries.putAll(toAPIGen.getMethodNamesToDescriptions());
	}

	private void addOverridingOperations(EClass xInitEClass, EClass initialisedEClass) {
		var createNowGen = new FluentAPISuperInitialisationCreateNowMethodGenerator();
		xInitEClass.getEOperations().addAll(createNowGen.generateAllCreateNowMethods(initialisedEClass));
		summaries.putAll(createNowGen.getMethodNamesToDescriptions());

		var dropGen = new FluentAPISuperInitialisationDropOperationGenerator();
		xInitEClass.getEOperations().add(dropGen.generateDropInitialisationMethod(xInitEClass));
		summaries.putAll(dropGen.getMethodNamesToDescriptions());

		var resetGen = new FluentAPISuperInitialisationResetOperationGenerator();
		xInitEClass.getEOperations().add(resetGen.generateResetInitialisationMethod(xInitEClass));
		summaries.putAll(resetGen.getMethodNamesToDescriptions());

		var nextInitGen = new FluentAPISuperInitialisationNextInitialisationMethodGenerator();
		xInitEClass.getEOperations().add(nextInitGen.getNextInitialisationMethodFor(xInitEClass, initialisedEClass));
		summaries.putAll(nextInitGen.getMethodNamesToDescriptions());

		var prevInitGen = new FluentAPISuperInitialisationPreviousInitialisationMethodGenerator();
		xInitEClass.getEOperations()
				.add(prevInitGen.getPreviousInitialisationMethodFor(xInitEClass, initialisedEClass));
		summaries.putAll(prevInitGen.getMethodNamesToDescriptions());

		var markGen = new FluentAPISuperInitialisationMarkMethodGenerator();
		xInitEClass.getEOperations().addAll(markGen.generateAllMarkMethods(xInitEClass));
		summaries.putAll(markGen.getMethodNamesToDescriptions());
	}

	private void addOperations(EClass xInitEClass, EClass initialisedEClass,
			FluentAPITargetMetamodelPackageProvider targetMetamodelPackageProvider,
			FluentAPITargetMetamodelFeatureFilter filter) {
		var onceExistsGen = new FluentAPIInitialisationOnceExistsMethodGenerator();
		xInitEClass.getEOperations().addAll(onceExistsGen.generateAllOnceExistsMethods(xInitEClass));
		summaries.putAll(onceExistsGen.getMethodNamesToDescriptions());

		var newElementGen = new FluentAPIInitialisationNewElementOperationGenerator();
		xInitEClass.getEOperations().add(newElementGen.getNewElementOperationFor(xInitEClass, initialisedEClass));
		summaries.putAll(newElementGen.getMethodNamesToDescriptions());

		var withGen = new FluentAPIInitialisationWithOperationGenerator();
		xInitEClass.getEOperations().addAll(withGen.generateAllWithOperationsFor(xInitEClass, initialisedEClass,
				targetMetamodelPackageProvider, filter));
		summaries.putAll(withGen.getMethodNamesToDescriptions());
	}
}
