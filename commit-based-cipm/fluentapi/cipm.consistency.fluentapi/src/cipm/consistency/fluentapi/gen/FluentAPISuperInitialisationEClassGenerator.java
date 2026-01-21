package cipm.consistency.fluentapi.gen;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.init.FluentAPICreateNowMethodGenerator;
import cipm.consistency.fluentapi.gen.init.FluentAPISuperInitialisationGetInitialisedEClassMethodGenerator;
import cipm.consistency.fluentapi.gen.init.FluentAPISuperInitialisationDropOperationGenerator;
import cipm.consistency.fluentapi.gen.init.FluentAPIInitialisationMarkMethodGenerator;
import cipm.consistency.fluentapi.gen.init.FluentAPIInitialisationResetOperationGenerator;
import cipm.consistency.fluentapi.gen.init.FluentAPISuperInitialisationNewElementMethodGenerator;
import cipm.consistency.fluentapi.gen.init.FluentAPISuperInitialisationWithOperationGenerator;
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
				.setName(FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationCurrentElementReferenceName());
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
		rootAPIRef.setName(FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationRootAPIReferenceName());
		rootAPIRef.setLowerBound(1);
		rootAPIRef.setUpperBound(1);
		return rootAPIRef;
	}

	public EClass generateSuperInitialisationEClass() {
		var superType = EcoreFactory.eINSTANCE.createEClass();
		superType.setAbstract(true);
		superType.setInterface(false);
		superType.setName(FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationClassName());
		return superType;
	}

	private void addRefs(EClass initSuperType, EClass fluentAPICls) {
		initSuperType.getEStructuralFeatures().add(getRootAPIReference(fluentAPICls));

		var currentElemRef = getCurrentElementReference(EcorePackage.Literals.EOBJECT);
		initSuperType.getEStructuralFeatures().add(currentElemRef);
	}

	private void addOperations(EClass initSuperType, EClass fluentAPICls) {
		initSuperType.getEOperations().add(new FluentAPISuperInitialisationGetInitialisedEClassMethodGenerator()
				.generateGetInitialisedEClassMethod());

		initSuperType.getEOperations().addAll(
				new FluentAPICreateNowMethodGenerator().generateAllCreateNowMethods(EcorePackage.Literals.EOBJECT));

		initSuperType.getEOperations().add(
				new FluentAPISuperInitialisationNewElementMethodGenerator().generateNewElementMethod(initSuperType));

		initSuperType.getEOperations().add(new FluentAPISuperInitialisationDropOperationGenerator()
				.generateDropInitialisationMethod(initSuperType));

		initSuperType.getEOperations().add(
				new FluentAPIInitialisationResetOperationGenerator().generateResetInitialisationMethod(initSuperType));

		initSuperType.getEOperations()
				.addAll(new FluentAPIInitialisationMarkMethodGenerator().generateAllMarkMethods(initSuperType));

		initSuperType.getEOperations().add(new FluentAPIToAPIMethodGenerator().generateToAPIMethod(fluentAPICls));

		initSuperType.getEOperations().add(new FluentAPINextInitialisationMethodGenerator()
				.getNextInitialisationMethodFor(initSuperType, EcorePackage.Literals.EOBJECT));

		initSuperType.getEOperations().add(new FluentAPIPreviousInitialisationMethodGenerator()
				.getPreviousInitialisationMethodFor(initSuperType, EcorePackage.Literals.EOBJECT));

		initSuperType.getEOperations().addAll(new FluentAPISuperInitialisationWithOperationGenerator()
				.getAllAPITopLevelWithOperations(initSuperType));
	}

	public void setupSuperInitialisationEClass(EClass fluentAPICls, EClass initSuperType) {
		addRefs(initSuperType, fluentAPICls);
		addOperations(initSuperType, fluentAPICls);
	}
}
