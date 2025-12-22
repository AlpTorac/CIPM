package cipm.consistency.fluentapi.gen;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;

import cipm.consistency.fluentapi.gen.init.FluentAPICreateNowMethodGenerator;
import cipm.consistency.fluentapi.gen.init.FluentAPIGetInitialisedEClassMethodGenerator;
import cipm.consistency.fluentapi.gen.init.FluentAPIInitialisationDropOperationGenerator;
import cipm.consistency.fluentapi.gen.init.FluentAPIInitialisationMarkMethodGenerator;
import cipm.consistency.fluentapi.gen.init.FluentAPIInitialisationResetOperationGenerator;
import cipm.consistency.fluentapi.gen.init.FluentAPINewElementMethodGenerator;
import cipm.consistency.fluentapi.gen.init.FluentAPINextInitialisationMethodGenerator;
import cipm.consistency.fluentapi.gen.init.FluentAPIPreviousInitialisationMethodGenerator;
import cipm.consistency.fluentapi.gen.init.FluentAPIToAPIMethodGenerator;

public class FluentAPISuperInitialisationEClassGenerator {
	private EReference getCurrentElementReference(EClass initialisedEClass) {
		var currentElementReference = EcoreFactory.eINSTANCE.createEReference();
		currentElementReference.setChangeable(true);
		currentElementReference.setContainment(false);
		currentElementReference.setEType(initialisedEClass);
		currentElementReference
				.setName(FluentAPIConstants.getFluentAPISuperInitialisationCurrentElementReferenceName());
		currentElementReference.setUnsettable(true);
		currentElementReference.setLowerBound(1);
		currentElementReference.setUpperBound(1);
		return currentElementReference;
	}

	private EReference getRootAPIReference(EClass rootAPIEClass) {
		var rootAPIRef = EcoreFactory.eINSTANCE.createEReference();
		rootAPIRef.setChangeable(true);
		rootAPIRef.setContainment(false);
		rootAPIRef.setEType(rootAPIEClass);
		rootAPIRef.setName(FluentAPIConstants.getFluentAPISuperInitialisationRootAPIReferenceName());
		rootAPIRef.setLowerBound(1);
		rootAPIRef.setUpperBound(1);
		return rootAPIRef;
	}

	public EClass generateSuperInitialisationEClass() {
		var superType = EcoreFactory.eINSTANCE.createEClass();
		superType.setAbstract(true);
		superType.setInterface(false);
		superType.setName(FluentAPIConstants.getFluentAPISuperInitialisationClassName());
		return superType;
	}

	private void addRefs(EClass initSuperType, EClass fluentAPICls) {
		initSuperType.getEStructuralFeatures().add(getRootAPIReference(fluentAPICls));

		var currentElemRef = getCurrentElementReference(FluentAPIGenerationUtil.getEObjectEClass());
		initSuperType.getEStructuralFeatures().add(currentElemRef);
	}

	private void addOperations(EClass initSuperType, EClass fluentAPICls) {
		initSuperType.getEOperations()
				.add(new FluentAPIGetInitialisedEClassMethodGenerator().generateGetInitialisedEClassMethod());

		initSuperType.getEOperations().add(new FluentAPICreateNowMethodGenerator()
				.generateCreateNowMethod(FluentAPIGenerationUtil.getEObjectEClass()));

		initSuperType.getEOperations()
				.add(new FluentAPINewElementMethodGenerator().generateNewElementMethod(initSuperType));

		initSuperType.getEOperations().add(
				new FluentAPIInitialisationDropOperationGenerator().generateDropInitialisationMethod(initSuperType));

		initSuperType.getEOperations().add(
				new FluentAPIInitialisationResetOperationGenerator().generateResetInitialisationMethod(initSuperType));

		initSuperType.getEOperations()
				.addAll(new FluentAPIInitialisationMarkMethodGenerator().generateAllMarkMethods(initSuperType));

		initSuperType.getEOperations().add(new FluentAPIToAPIMethodGenerator().generateToAPIMethod(fluentAPICls));

		initSuperType.getEOperations().add(new FluentAPINextInitialisationMethodGenerator()
				.getNextInitialisationMethodFor(initSuperType, FluentAPIGenerationUtil.getEObjectEClass()));

		initSuperType.getEOperations().add(new FluentAPIPreviousInitialisationMethodGenerator()
				.getPreviousInitialisationMethodFor(initSuperType, FluentAPIGenerationUtil.getEObjectEClass()));
	}

	public void setupSuperInitialisationEClass(EClass fluentAPICls, EClass initSuperType) {
		addRefs(initSuperType, fluentAPICls);
		addOperations(initSuperType, fluentAPICls);
	}
}
