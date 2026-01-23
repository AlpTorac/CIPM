package cipm.consistency.fluentapi.gen.superinit;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;

public class FluentAPISuperInitialisationEClassGenerator {
	private EReference getCurrentElementReference(EClass initialisedEClass) {
		var currentElementReference = EcoreFactory.eINSTANCE.createEReference();
		currentElementReference.setChangeable(true);
		currentElementReference.setContainment(false);
		currentElementReference.setEType(initialisedEClass);
		currentElementReference.setName(
				FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationCurrentElementReferenceName());
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

		initSuperType.getEOperations().addAll(new FluentAPISuperInitialisationCreateNowMethodGenerator()
				.generateAllCreateNowMethods(EcorePackage.Literals.EOBJECT));

		initSuperType.getEOperations().add(
				new FluentAPISuperInitialisationNewElementMethodGenerator().generateNewElementMethod(initSuperType));

		initSuperType.getEOperations().add(new FluentAPISuperInitialisationDropOperationGenerator()
				.generateDropInitialisationMethod(initSuperType));

		initSuperType.getEOperations().add(new FluentAPISuperInitialisationResetOperationGenerator()
				.generateResetInitialisationMethod(initSuperType));

		initSuperType.getEOperations()
				.addAll(new FluentAPISuperInitialisationMarkMethodGenerator().generateAllMarkMethods(initSuperType));

		initSuperType.getEOperations().add(new FluentAPISuperInitialisationToAPIMethodGenerator().generateToAPIMethod(fluentAPICls));

		initSuperType.getEOperations().add(new FluentAPISuperInitialisationNextInitialisationMethodGenerator()
				.getNextInitialisationMethodFor(initSuperType, EcorePackage.Literals.EOBJECT));

		initSuperType.getEOperations().add(new FluentAPISuperInitialisationPreviousInitialisationMethodGenerator()
				.getPreviousInitialisationMethodFor(initSuperType, EcorePackage.Literals.EOBJECT));

		initSuperType.getEOperations().addAll(new FluentAPISuperInitialisationWithOperationGenerator()
				.getAllAPITopLevelWithOperations(initSuperType));

		initSuperType.getEOperations().addAll(new FluentAPISuperInitialisationOnceExistsMethodGenerator()
				.generateAllOnceExistsMethods(initSuperType));
	}

	public void setupSuperInitialisationEClass(EClass fluentAPICls, EClass initSuperType) {
		addRefs(initSuperType, fluentAPICls);
		addOperations(initSuperType, fluentAPICls);
	}
}
