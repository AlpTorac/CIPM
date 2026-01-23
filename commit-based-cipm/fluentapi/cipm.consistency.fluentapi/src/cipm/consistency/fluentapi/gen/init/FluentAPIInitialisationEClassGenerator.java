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
import cipm.consistency.fluentapi.gen.superinit.FluentAPISuperInitialisationMarkMethodGenerator;
import cipm.consistency.fluentapi.gen.superinit.FluentAPISuperInitialisationNextInitialisationMethodGenerator;
import cipm.consistency.fluentapi.gen.superinit.FluentAPISuperInitialisationPreviousInitialisationMethodGenerator;
import cipm.consistency.fluentapi.gen.superinit.FluentAPISuperInitialisationResetOperationGenerator;

public class FluentAPIInitialisationEClassGenerator {
	// %s: Initialised class name
	// %s: Metamodel name
	// %s: Initialised class name
	// %s: Serialised method names and summaries
	private static final String initClassDocTemplate = "An Initialisation class that targets the type '%s' within the '%s' metamodel. Contains various methods that facilitate the programmatic construction of '%s' instances. It is recommended to only use the methods presented below. In the following, replace 'X's with the concrete feature name:"
			+ FluentAPIGenerationUtil.getDocParagraphSeparator() + "<ul>%s</ul>";

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
		addOperations(xInitEClass, initialisedEClass, targetMetamodelPackageProvider, filter);
	}

	private void addOperations(EClass xInitEClass, EClass initialisedEClass,
			FluentAPITargetMetamodelPackageProvider targetMetamodelPackageProvider,
			FluentAPITargetMetamodelFeatureFilter filter) {
		xInitEClass.getEOperations().addAll(new FluentAPISuperInitialisationCreateNowMethodGenerator()
				.generateAllCreateNowMethods(initialisedEClass));

		xInitEClass.getEOperations().add(
				new FluentAPISuperInitialisationDropOperationGenerator().generateDropInitialisationMethod(xInitEClass));

		xInitEClass.getEOperations().add(new FluentAPISuperInitialisationResetOperationGenerator()
				.generateResetInitialisationMethod(xInitEClass));

		xInitEClass.getEOperations().add(new FluentAPISuperInitialisationNextInitialisationMethodGenerator()
				.getNextInitialisationMethodFor(xInitEClass, initialisedEClass));

		xInitEClass.getEOperations().add(new FluentAPISuperInitialisationPreviousInitialisationMethodGenerator()
				.getPreviousInitialisationMethodFor(xInitEClass, initialisedEClass));

		xInitEClass.getEOperations()
				.addAll(new FluentAPISuperInitialisationMarkMethodGenerator().generateAllMarkMethods(xInitEClass));

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

		addXInitEClassDocumentation(xInitEClass, initialisedEClass, targetMetamodelPackageProvider);
	}
}
