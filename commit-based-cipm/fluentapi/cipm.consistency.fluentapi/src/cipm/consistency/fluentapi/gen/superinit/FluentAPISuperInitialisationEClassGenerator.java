package cipm.consistency.fluentapi.gen.superinit;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIDocumentationUtil;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;

public class FluentAPISuperInitialisationEClassGenerator {
	private static final String initClassDocTemplate = "The top-most Initialisation class, which all concrete initialisation classes extend. Contains various methods that facilitate the programmatic construction of model object instances."
			+ FluentAPIDocumentationUtil.getClassMethodOverviewIntroTemplate();

	private static final Map<String, String> summaries = new LinkedHashMap<>();

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

	private void addEClassDoc(EClass initSuperType) {
		var doc = String.format(initClassDocTemplate, FluentAPIDocumentationUtil.serialiseSummaries(summaries));
		FluentAPIGenerationUtil.addDocumentation(initSuperType, doc);
	}

	private void addRefs(EClass initSuperType, EClass fluentAPICls) {
		initSuperType.getEStructuralFeatures().add(getRootAPIReference(fluentAPICls));

		var currentElemRef = getCurrentElementReference(EcorePackage.Literals.EOBJECT);
		initSuperType.getEStructuralFeatures().add(currentElemRef);
	}

	private void addOperations(EClass initSuperType, EClass fluentAPICls) {
		var getInitEClsGen = new FluentAPISuperInitialisationGetInitialisedEClassMethodGenerator();
		initSuperType.getEOperations().add(getInitEClsGen.generateGetInitialisedEClassMethod());
		summaries.putAll(getInitEClsGen.getMethodNamesToDescriptions());

		var createNowGen = new FluentAPISuperInitialisationCreateNowMethodGenerator();
		initSuperType.getEOperations().addAll(createNowGen.generateAllCreateNowMethods(EcorePackage.Literals.EOBJECT));
		summaries.putAll(createNowGen.getMethodNamesToDescriptions());

		var newElemGen = new FluentAPISuperInitialisationNewElementMethodGenerator();
		initSuperType.getEOperations().add(newElemGen.generateNewElementMethod(initSuperType));
		summaries.putAll(newElemGen.getMethodNamesToDescriptions());

		var dropGen = new FluentAPISuperInitialisationDropOperationGenerator();
		initSuperType.getEOperations().add(dropGen.generateDropInitialisationMethod(initSuperType));
		summaries.putAll(dropGen.getMethodNamesToDescriptions());

		var resetGen = new FluentAPISuperInitialisationResetOperationGenerator();
		initSuperType.getEOperations().add(resetGen.generateResetInitialisationMethod(initSuperType));
		summaries.putAll(resetGen.getMethodNamesToDescriptions());

		var markGen = new FluentAPISuperInitialisationMarkMethodGenerator();
		initSuperType.getEOperations().addAll(markGen.generateAllMarkMethods(initSuperType));
		summaries.putAll(markGen.getMethodNamesToDescriptions());

		var toAPIGen = new FluentAPISuperInitialisationToAPIMethodGenerator();
		initSuperType.getEOperations().add(toAPIGen.generateToAPIMethod(fluentAPICls));
		summaries.putAll(toAPIGen.getMethodNamesToDescriptions());

		var nextInitGen = new FluentAPISuperInitialisationNextInitialisationMethodGenerator();
		initSuperType.getEOperations()
				.addAll(nextInitGen.getAllNextInitialisationMethods(initSuperType, EcorePackage.Literals.EOBJECT));
		summaries.putAll(nextInitGen.getMethodNamesToDescriptions());

		var prevInitGen = new FluentAPISuperInitialisationPreviousInitialisationMethodGenerator();
		initSuperType.getEOperations()
				.addAll(prevInitGen.getAllPreviousInitialisationMethods(initSuperType, EcorePackage.Literals.EOBJECT));
		summaries.putAll(prevInitGen.getMethodNamesToDescriptions());

		var withGen = new FluentAPISuperInitialisationWithOperationGenerator();
		initSuperType.getEOperations().addAll(withGen.getAllAPITopLevelWithOperations(initSuperType));
		summaries.putAll(withGen.getMethodNamesToDescriptions());

		var onceExistsGen = new FluentAPISuperInitialisationOnceExistsMethodGenerator();
		initSuperType.getEOperations().addAll(onceExistsGen.generateAllOnceExistsMethods(initSuperType));
		summaries.putAll(onceExistsGen.getMethodNamesToDescriptions());
	}

	public void setupSuperInitialisationEClass(EClass fluentAPICls, EClass initSuperType) {
		addRefs(initSuperType, fluentAPICls);
		addOperations(initSuperType, fluentAPICls);
		addEClassDoc(initSuperType);
	}
}
