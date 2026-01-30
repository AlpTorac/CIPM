package cipm.consistency.fluentapi.gen.rootapi;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
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
	private static final String initialisationsReferenceDoc = "<p>"
			+ FluentAPIRootAPIConstants.getRootAPIInitialisationsReferenceName()
			+ " contains references to each concrete Initialisation class within this API. These references are used to create the necessary "
			+ FluentAPIInitialisationConstants.getFluentAPIInitialisationClassNameSuffix()
			+ " instance, in order to create a certain element. As API generation considers arbitrary EMF-based metamodels, this reference allows systematic access to supported "
			+ FluentAPIInitialisationConstants.getFluentAPIInitialisationClassNameSuffix()
			+ " classes. The contents of this reference should not be modified post API generation.";
	private static final String ongoingInitsDoc = "<p>"
			+ FluentAPIRootAPIConstants.getRootAPIOngoingInitialisationsReferenceName() + " contains each "
			+ FluentAPIInitialisationConstants.getFluentAPIInitialisationClassNameSuffix()
			+ " instance, which encapsulate a non-finished element construction, that this class created. This allows the API to find unfinished "
			+ FluentAPIInitialisationConstants.getFluentAPIInitialisationClassNameSuffix()
			+ " instances in a systematic way. The contents of this reference should only be modified by the foreseen methods in this class.";

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

	/**
	 * {@value #rootAPIInitialisationsReferenceName} is a many-valued,
	 * non-containment EReference containing EClass instances. It is assumed that
	 * these EClasses each belong to a XInitialisation type.
	 * 
	 * @return The EReference responsible for containing EClass of each concrete
	 *         initialisation XInitialisation
	 */
	private EReference getInitialisationEClassesReference() {
		var initialisationsRef = EcoreFactory.eINSTANCE.createEReference();

		var anno = EcoreFactory.eINSTANCE.createEAnnotation();
		anno.setSource(FluentAPIConstants.getGenModelURL());
		anno.getDetails().put(FluentAPIGenerationUtil.getEOperationDocumentationKey(), initialisationsReferenceDoc);

		initialisationsRef.getEAnnotations().add(anno);

		initialisationsRef.setChangeable(true);
		initialisationsRef.setContainment(false);
		initialisationsRef.setEType(EcoreFactory.eINSTANCE.createEClass().eClass());
		initialisationsRef.setName(FluentAPIRootAPIConstants.getRootAPIInitialisationsReferenceName());
		initialisationsRef.setLowerBound(0);
		initialisationsRef.setUpperBound(EReference.UNBOUNDED_MULTIPLICITY);
		return initialisationsRef;
	}

	/**
	 * {@value #rootAPIInitialisationsReferenceName} is a many-valued,
	 * non-containment EReference containing Initialisation instances.
	 * 
	 * @return The EReference responsible for containing each concrete
	 *         initialisation Initialisation belonging to an ongoing initialisation
	 */
	private EReference getOngoingInitialisationsReference(EClass initsSuperTypeEClass) {
		var ongoingInitsRef = EcoreFactory.eINSTANCE.createEReference();

		var anno = EcoreFactory.eINSTANCE.createEAnnotation();
		anno.setSource(FluentAPIConstants.getGenModelURL());
		anno.getDetails().put(FluentAPIGenerationUtil.getEOperationDocumentationKey(), ongoingInitsDoc);

		ongoingInitsRef.getEAnnotations().add(anno);

		ongoingInitsRef.setChangeable(true);
		ongoingInitsRef.setContainment(false);
		ongoingInitsRef.setEType(initsSuperTypeEClass);
		ongoingInitsRef.setName(FluentAPIRootAPIConstants.getRootAPIOngoingInitialisationsReferenceName());
		ongoingInitsRef.setLowerBound(0);
		ongoingInitsRef.setUpperBound(EReference.UNBOUNDED_MULTIPLICITY);
		return ongoingInitsRef;
	}

	private void addRefs(EClass fluentAPICls, EClass initSuperType) {
		var initialisationsRef = getInitialisationEClassesReference();
		fluentAPICls.getEStructuralFeatures().add(initialisationsRef);

		var ongoingInitialisationsRef = getOngoingInitialisationsReference(initSuperType);
		fluentAPICls.getEStructuralFeatures().add(ongoingInitialisationsRef);
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
		addRefs(fluentAPICls, initSuperType);
		addOperations(fluentAPICls, initSuperType, initEClss, targetMetamodelPackageProvider, filter);
	}
}
