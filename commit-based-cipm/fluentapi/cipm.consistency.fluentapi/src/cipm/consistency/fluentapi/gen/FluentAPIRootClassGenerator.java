package cipm.consistency.fluentapi.gen;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;

import cipm.consistency.fluentapi.gen.rootapi.FluentAPIContinueMethodGenerator;
import cipm.consistency.fluentapi.gen.rootapi.FluentAPIDropInitialisationMethodGenerator;
import cipm.consistency.fluentapi.gen.rootapi.FluentAPIGetInitialisationForMethodGenerator;
import cipm.consistency.fluentapi.gen.rootapi.FluentAPIModifyElementMethodGenerator;
import cipm.consistency.fluentapi.gen.rootapi.FluentAPIRootAPIMarkMethodGenerator;
import cipm.consistency.fluentapi.gen.rootapi.FluentAPIRootAPINewMethodGenerator;
import cipm.consistency.fluentapi.gen.rootapi.FluentAPIRootAPIOnceExistsMethodGenerator;
import cipm.consistency.fluentapi.gen.rootapi.FluentAPIRootAPIWithOperationGenerator;

public class FluentAPIRootClassGenerator {
	public EClass generateRootAPIEClass() {
		var fluentAPICls = EcoreFactory.eINSTANCE.createEClass();
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
	}

	public void setupRootAPIEClass(EClass fluentAPICls, EClass initSuperType, List<EClass> initEClss,
			FluentAPITargetMetamodelPackageProvider targetMetamodelPackageProvider,
			FluentAPITargetMetamodelFeatureFilter filter) {
		var allEClassesToInit = targetMetamodelPackageProvider.getAllTargetMetamodelConcreteEClasses();

		addRefs(fluentAPICls, initSuperType);
		addOperations(fluentAPICls, initSuperType, initEClss, allEClassesToInit, filter);
	}
}
