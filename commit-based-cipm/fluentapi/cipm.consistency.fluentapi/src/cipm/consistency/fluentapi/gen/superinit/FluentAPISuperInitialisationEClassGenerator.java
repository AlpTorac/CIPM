package cipm.consistency.fluentapi.gen.superinit;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIDocumentationUtil;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationContext;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;

public class FluentAPISuperInitialisationEClassGenerator {
	private static final String initClassDocTemplate = "The top-most Initialisation class, which all concrete initialisation classes extend. Contains various methods that facilitate the programmatic construction of model object instances."
			+ FluentAPIDocumentationUtil.getClassMethodOverviewIntroTemplate();

	private static final Map<String, String> summaries = new LinkedHashMap<>();

	private EReference getCurrentElementReference() {
		var currentElementReference = EcoreFactory.eINSTANCE.createEReference();
		currentElementReference.setChangeable(true);
		currentElementReference.setContainment(false);
		currentElementReference.setEType(EcorePackage.Literals.EOBJECT);
		currentElementReference.setName(
				FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationCurrentElementReferenceName());
		currentElementReference.setUnsettable(true);
		currentElementReference.setLowerBound(1);
		currentElementReference.setUpperBound(1);
		return currentElementReference;
	}

	private EReference getRootAPIReference(FluentAPIGenerationContext context) {
		var rootAPIRef = EcoreFactory.eINSTANCE.createEReference();
		rootAPIRef.setChangeable(true);
		rootAPIRef.setContainment(false);
		rootAPIRef.setEType(context.getFluentAPIECls());
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

	private void addEClassDoc(FluentAPIGenerationContext context) {
		var doc = String.format(initClassDocTemplate, FluentAPIDocumentationUtil.serialiseSummaries(summaries));
		FluentAPIGenerationUtil.addDocumentation(context.getInitSuperECls(), doc);
	}

	private void addRefs(FluentAPIGenerationContext context) {
		context.setInitSuperEClsApiReference(getRootAPIReference(context));
		context.getInitSuperECls().getEStructuralFeatures().add(context.getInitSuperEClsApiReference());

		context.setInitSuperEClsCurrentElement(getCurrentElementReference());
		context.getInitSuperECls().getEStructuralFeatures().add(context.getInitSuperEClsCurrentElement());
	}

	private void addOperations(FluentAPIGenerationContext context) {
		var getInitEClsGen = new FluentAPISuperInitialisationGetInitialisedEClassMethodGenerator();
		context.getInitSuperECls().getEOperations().add(getInitEClsGen.generateGetInitialisedEClassMethod());
		summaries.putAll(getInitEClsGen.getMethodNamesToDescriptions());

		var createNowGen = new FluentAPISuperInitialisationCreateNowMethodGenerator();
		context.getInitSuperECls().getEOperations().addAll(createNowGen.generateAllCreateNowMethods(EcorePackage.Literals.EOBJECT));
		summaries.putAll(createNowGen.getMethodNamesToDescriptions());

		var newElemGen = new FluentAPISuperInitialisationNewElementMethodGenerator();
		context.getInitSuperECls().getEOperations().add(newElemGen.generateNewElementMethod(context));
		summaries.putAll(newElemGen.getMethodNamesToDescriptions());

//		var dropGen = new FluentAPISuperInitialisationDropOperationGenerator();
//		context.getInitSuperECls().getEOperations().add(dropGen.generateDropInitialisationMethod(context.getInitSuperECls()));
//		summaries.putAll(dropGen.getMethodNamesToDescriptions());

		var resetGen = new FluentAPISuperInitialisationResetOperationGenerator();
		context.getInitSuperECls().getEOperations().add(resetGen.generateResetInitialisationMethod(context.getInitSuperECls()));
		summaries.putAll(resetGen.getMethodNamesToDescriptions());

//		var markGen = new FluentAPISuperInitialisationMarkMethodGenerator();
//		context.getInitSuperECls().getEOperations().addAll(markGen.generateAllMarkMethods(context.getInitSuperECls()));
//		summaries.putAll(markGen.getMethodNamesToDescriptions());

		var toAPIGen = new FluentAPISuperInitialisationToAPIMethodGenerator();
		context.getInitSuperECls().getEOperations().add(toAPIGen.generateToAPIMethod(context));
		summaries.putAll(toAPIGen.getMethodNamesToDescriptions());

		var nextInitGen = new FluentAPISuperInitialisationNextInitialisationMethodGenerator();
		context.getInitSuperECls().getEOperations()
				.addAll(nextInitGen.getAllNextInitialisationMethods(context.getInitSuperECls(), EcorePackage.Literals.EOBJECT));
		summaries.putAll(nextInitGen.getMethodNamesToDescriptions());

		var prevInitGen = new FluentAPISuperInitialisationPreviousInitialisationMethodGenerator();
		context.getInitSuperECls().getEOperations()
				.addAll(prevInitGen.getAllPreviousInitialisationMethods(context.getInitSuperECls(), EcorePackage.Literals.EOBJECT));
		summaries.putAll(prevInitGen.getMethodNamesToDescriptions());

//		var withGen = new FluentAPISuperInitialisationWithOperationGenerator();
//		context.getInitSuperECls().getEOperations().addAll(withGen.getAllAPITopLevelWithOperations(context));
//		summaries.putAll(withGen.getMethodNamesToDescriptions());
//
//		var onceExistsGen = new FluentAPISuperInitialisationOnceExistsMethodGenerator();
//		context.getInitSuperECls().getEOperations().add(onceExistsGen.generateAllOnceExistsMethods(context));
//		summaries.putAll(onceExistsGen.getMethodNamesToDescriptions());
		
		var delegateOpGen = new FluentAPISuperInitialisationDelegateMethodGenerator();
		context.getInitSuperECls().getEOperations().addAll(delegateOpGen.generateAllDelegateMethods(context));
	}

	public void setupSuperInitialisationEClass(FluentAPIGenerationContext context) {
		addRefs(context);
		addOperations(context);
		addEClassDoc(context);
	}
}
