package cipm.consistency.fluentapi.gen.rootapi;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcoreFactory;

import cipm.consistency.fluentapi.gen.FluentAPIConstants;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelFeatureFilter;
import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelPackageProvider;
import cipm.consistency.fluentapi.gen.init.FluentAPIInitialisationConstants;

public class FluentAPIRootAPIEClassGenerator {

	/*
	 * TODO If possible, add a way to generate further convenience methods, such as
	 * "api.newClassifierReferenceWithTarget(classifier)" in:
	 * 
	 * api.newClass().withExtends(api.newClassifierReferenceWithTarget(classifier)).
	 * createNow()
	 * 
	 * api.newClass().withExtends(api.newClassifierReference().withTarget(classifier
	 * ).createNow()).createNow()
	 * 
	 *
	 * Possible strategies to determine such methods: 1) List of frequently used
	 * constructions 2) Deterministic strategies (heuristics) over various metamodel
	 * properties
	 */

	// TODO Re-use / link to documentations of mentioned API classes

	// TODO Mention for each method template what it more or less does, re-use or
	// link to their documentation
	private static final String rootAPIClassDoc = "<p>" + FluentAPIRootAPIConstants.getFluentAPIRootAPIClassName()
			+ " is at the center of the fluent API and enables creation of EObject sub-types within the EMF-based metamodel MM this API targets. To this end, this class offers various methods that lead to underlying "
			+ FluentAPIInitialisationConstants.getFluentAPIInitialisationClassNameSuffix()
			+ " classes, each being responsible for a concrete element from MM. For more information on what individual EObject sub-types and their features represent, refer to MM's documentation.";

	public EClass generateRootAPIEClass() {
		var fluentAPICls = EcoreFactory.eINSTANCE.createEClass();

		var anno = EcoreFactory.eINSTANCE.createEAnnotation();
		anno.setSource(FluentAPIConstants.getGenModelURL());
		anno.getDetails().put(FluentAPIGenerationUtil.getEOperationDocumentationKey(), rootAPIClassDoc);

		fluentAPICls.getEAnnotations().add(anno);

		fluentAPICls.setAbstract(false);
		fluentAPICls.setInterface(false);
		fluentAPICls.setName(FluentAPIRootAPIConstants.getFluentAPIRootAPIClassName());
		return fluentAPICls;
	}

	private void addOperations(EClass fluentAPICls, EClass initSuperType, List<EClass> initEClss,
			FluentAPITargetMetamodelPackageProvider targetMetamodelPackageProvider,
			FluentAPITargetMetamodelFeatureFilter filter) {

		fluentAPICls.getEOperations().addAll(new FluentAPIRootAPICreateNewMethodGenerator()
				.generateAllCreateNewMethods(targetMetamodelPackageProvider));

		fluentAPICls.getEOperations().addAll(new FluentAPIRootAPINewMethodGenerator().getAllRootAPINewOperations(
				fluentAPICls, initSuperType, initEClss, targetMetamodelPackageProvider, filter));

		fluentAPICls.getEOperations().addAll(
				new FluentAPIRootAPIModifyElementMethodGenerator().getAllRootAPIModifyElementOperations(fluentAPICls,
						initSuperType, initEClss, targetMetamodelPackageProvider));

		fluentAPICls.getEOperations().addAll(new FluentAPIRootAPIContinueMethodGenerator()
				.generateAllContinueMethods(initSuperType, initEClss, targetMetamodelPackageProvider, filter));

		fluentAPICls.getEOperations().add(new FluentAPIRootAPIDropInitialisationMethodGenerator()
				.generateDropInitialisationMethod(fluentAPICls, initSuperType));

		fluentAPICls.getEOperations().addAll(new FluentAPIRootAPIMarkMethodGenerator()
				.generateAllMarkMethods(fluentAPICls, targetMetamodelPackageProvider));

		fluentAPICls.getEOperations()
				.addAll(new FluentAPIRootAPIOnceExistsMethodGenerator().generateAllOnceExistsMethods(fluentAPICls));

		fluentAPICls.getEOperations().add(new FluentAPIRootAPIGetInitialisationForMethodGenerator()
				.getInitialisationForEClassMethod(initSuperType));

		fluentAPICls.getEOperations().add(new FluentAPIRootAPIGetInitialisationForMethodGenerator()
				.getInitialisationForClassMethod(initSuperType));

		fluentAPICls.getEOperations().add(new FluentAPIRootAPIGetInitialisationForMethodGenerator()
				.getInitialisationForEObjectMethod(initSuperType));

		fluentAPICls.getEOperations()
				.addAll(new FluentAPIRootAPIWithOperationGenerator().getAllAPITopLevelWithOperations(fluentAPICls));

		fluentAPICls.getEOperations().add(new FluentAPIRootAPIGetAllSupportedClassesMethodGenerator()
				.generateGetAllSupportedClassesMethodGenerator(fluentAPICls));
	}

	public void setupRootAPIEClass(EClass fluentAPICls, EClass initSuperType, List<EClass> initEClss,
			FluentAPITargetMetamodelPackageProvider targetMetamodelPackageProvider,
			FluentAPITargetMetamodelFeatureFilter filter) {
		addOperations(fluentAPICls, initSuperType, initEClss, targetMetamodelPackageProvider, filter);
	}
}
