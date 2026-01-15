package cipm.consistency.fluentapi.gen;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;

import cipm.consistency.fluentapi.gen.rootapi.FluentAPIContinueMethodGenerator;
import cipm.consistency.fluentapi.gen.rootapi.FluentAPIDropInitialisationMethodGenerator;
import cipm.consistency.fluentapi.gen.rootapi.FluentAPIGetAllSupportedEClassesMethodGenerator;
import cipm.consistency.fluentapi.gen.rootapi.FluentAPIGetInitialisationForMethodGenerator;
import cipm.consistency.fluentapi.gen.rootapi.FluentAPIModifyElementMethodGenerator;
import cipm.consistency.fluentapi.gen.rootapi.FluentAPIRootAPIMarkMethodGenerator;
import cipm.consistency.fluentapi.gen.rootapi.FluentAPIRootAPINewMethodGenerator;
import cipm.consistency.fluentapi.gen.rootapi.FluentAPIRootAPIOnceExistsMethodGenerator;
import cipm.consistency.fluentapi.gen.rootapi.FluentAPIRootAPIWithOperationGenerator;

public class FluentAPIRootClassGenerator {

	// TODO Re-use / link to documentations of mentioned API classes

	// TODO Mention for each method template what it more or less does, re-use or
	// link to their documentation
	private static final String rootAPIClassDoc = "<p>" + FluentAPIConstants.getFluentAPIRootAPIClassName()
			+ " is at the center of the fluent API and enables creation of EObject sub-types within the EMF-based metamodel MM this API targets. To this end, this class offers various methods that lead to underlying Initialisation classes, each being responsible for a concrete element from MM. For more information on what individual EObject sub-types and their features represent, refer to MM's documentation.";
	private static final String initialisationsReferenceDoc = "<p>"
			+ FluentAPIConstants.getRootAPIInitialisationsReferenceName()
			+ " contains references to each concrete Initialisation class within this API. These references are used to create the necessary Initialisation instance, in order to create a certain element. As API generation considers arbitrary EMF-based metamodels, this reference allows systematic access to supported Initialisation classes. The contents of this reference should not be modified post API generation.";
	private static final String ongoingInitsDoc = "<p>"
			+ FluentAPIConstants.getRootAPIOngoingInitialisationsReferenceName()
			+ " contains each Initialisation instance, which encapsulate a non-finished element construction, that this class created. This allows the API to find such Initialisation instances in a systematic way. The contents of this reference should only be modified by the foreseen methods in this class.";

	public EClass generateRootAPIEClass() {
		var fluentAPICls = EcoreFactory.eINSTANCE.createEClass();

		var anno = EcoreFactory.eINSTANCE.createEAnnotation();
		anno.setSource(FluentAPIConstants.getGenModelURL());
		anno.getDetails().put(FluentAPIGenerationUtil.getEOperationDocumentationKey(), rootAPIClassDoc);

		fluentAPICls.getEAnnotations().add(anno);

		fluentAPICls.setAbstract(false);
		fluentAPICls.setInterface(false);
		fluentAPICls.setName(FluentAPIConstants.getFluentAPIRootAPIClassName());
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
		initialisationsRef.setName(FluentAPIConstants.getRootAPIInitialisationsReferenceName());
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
		ongoingInitsRef.setName(FluentAPIConstants.getRootAPIOngoingInitialisationsReferenceName());
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
			List<EClass> allEClassesToInit, FluentAPITargetMetamodelFeatureFilter filter) {

		fluentAPICls.getEOperations().addAll(new FluentAPIRootAPINewMethodGenerator()
				.getAllRootAPINewOperations(fluentAPICls, initSuperType, initEClss, allEClassesToInit, filter));

		fluentAPICls.getEOperations().addAll(new FluentAPIModifyElementMethodGenerator()
				.getAllRootAPIModifyElementOperations(fluentAPICls, initSuperType, initEClss, allEClassesToInit));

		fluentAPICls.getEOperations().addAll(new FluentAPIContinueMethodGenerator()
				.generateAllContinueMethods(initEClss, allEClassesToInit, filter));

		fluentAPICls.getEOperations().add(new FluentAPIDropInitialisationMethodGenerator()
				.generateDropInitialisationMethod(fluentAPICls, initSuperType));

		fluentAPICls.getEOperations().addAll(
				new FluentAPIRootAPIMarkMethodGenerator().generateAllMarkMethods(fluentAPICls, allEClassesToInit));

		fluentAPICls.getEOperations()
				.addAll(new FluentAPIRootAPIOnceExistsMethodGenerator().generateAllOnceExistsMethods(fluentAPICls));

		fluentAPICls.getEOperations().add(
				new FluentAPIGetInitialisationForMethodGenerator().getInitialisationForEClassMethod(initSuperType));

		fluentAPICls.getEOperations()
				.add(new FluentAPIGetInitialisationForMethodGenerator().getInitialisationForClassMethod(initSuperType));

		fluentAPICls.getEOperations().add(
				new FluentAPIGetInitialisationForMethodGenerator().getInitialisationForEObjectMethod(initSuperType));

		fluentAPICls.getEOperations()
				.addAll(new FluentAPIRootAPIWithOperationGenerator().getAllAPITopLevelWithOperations(fluentAPICls));

		fluentAPICls.getEOperations().add(new FluentAPIGetAllSupportedEClassesMethodGenerator()
				.generateGetAllSupportedEClassesMethodGenerator(fluentAPICls));
	}

	public void setupRootAPIEClass(EClass fluentAPICls, EClass initSuperType, List<EClass> initEClss,
			FluentAPITargetMetamodelPackageProvider targetMetamodelPackageProvider,
			FluentAPITargetMetamodelFeatureFilter filter) {
		var allEClassesToInit = targetMetamodelPackageProvider.getAllTargetMetamodelConcreteEClasses();

		addRefs(fluentAPICls, initSuperType);
		addOperations(fluentAPICls, initSuperType, initEClss, allEClassesToInit, filter);
	}
}
