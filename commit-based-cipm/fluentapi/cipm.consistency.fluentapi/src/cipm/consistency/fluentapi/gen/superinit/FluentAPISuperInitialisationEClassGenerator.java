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
import cipm.consistency.fluentapi.gen.ModelConstants;

public class FluentAPISuperInitialisationEClassGenerator {

	private static final Map<String, String> summaries = new LinkedHashMap<>();

	private EReference getCurrentElementReference() {
		var currentElementReference = EcoreFactory.eINSTANCE.createEReference();
		currentElementReference.setChangeable(true);
		currentElementReference.setContainment(false);
		currentElementReference.setEType(EcorePackage.Literals.EOBJECT);
		currentElementReference.setName(ModelConstants.SuperInitialisation.CurrentElement.NAME.get());
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
		rootAPIRef.setName(ModelConstants.SuperInitialisation.RootAPI.NAME.get());
		rootAPIRef.setLowerBound(1);
		rootAPIRef.setUpperBound(1);
		return rootAPIRef;
	}

	public EClass generateSuperInitialisationEClass() {
		var superType = EcoreFactory.eINSTANCE.createEClass();
		superType.setAbstract(true);
		superType.setInterface(false);
		superType.setName(ModelConstants.SuperInitialisation.CLASS_NAME.get());
		return superType;
	}

	private void addEClassDoc(FluentAPIGenerationContext context) {
		var doc = ModelConstants.SuperInitialisation.CLASS_DOC
				.getFor(FluentAPIDocumentationUtil.serialiseSummaries(summaries));
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
		context.getInitSuperECls().getEOperations()
				.addAll(createNowGen.generateAllCreateNowMethods(EcorePackage.Literals.EOBJECT));
		summaries.putAll(createNowGen.getMethodNamesToDescriptions());

		var newElemGen = new FluentAPISuperInitialisationNewElementMethodGenerator();
		context.getInitSuperECls().getEOperations().add(newElemGen.generateNewElementMethod(context));
		summaries.putAll(newElemGen.getMethodNamesToDescriptions());

		var resetGen = new FluentAPISuperInitialisationResetOperationGenerator();
		context.getInitSuperECls().getEOperations()
				.add(resetGen.generateResetInitialisationMethod(context.getInitSuperECls()));
		summaries.putAll(resetGen.getMethodNamesToDescriptions());

		var toAPIGen = new FluentAPISuperInitialisationToAPIMethodGenerator();
		context.getInitSuperECls().getEOperations().add(toAPIGen.generateToAPIMethod(context));
		summaries.putAll(toAPIGen.getMethodNamesToDescriptions());

		var delegateOpGen = new FluentAPISuperInitialisationDelegateMethodGenerator();
		context.getInitSuperECls().getEOperations().addAll(delegateOpGen.generateAllDelegateMethods(context));
	}

	public void setupSuperInitialisationEClass(FluentAPIGenerationContext context) {
		addRefs(context);
		addOperations(context);
		addEClassDoc(context);
	}
}
