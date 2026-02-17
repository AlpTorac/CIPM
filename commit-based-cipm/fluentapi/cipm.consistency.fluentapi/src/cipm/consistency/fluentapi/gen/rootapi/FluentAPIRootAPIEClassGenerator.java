package cipm.consistency.fluentapi.gen.rootapi;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcoreFactory;

import cipm.consistency.fluentapi.gen.FluentAPIConstants;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationContext;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.init.FluentAPIInitialisationConstants;

public class FluentAPIRootAPIEClassGenerator {

	// TODO Add a getOngoingInits() method to hide FluentAPIInitialisationStorage

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
		var fluentAPIECls = EcoreFactory.eINSTANCE.createEClass();

		var anno = EcoreFactory.eINSTANCE.createEAnnotation();
		anno.setSource(FluentAPIConstants.getGenModelURL());
		anno.getDetails().put(FluentAPIGenerationUtil.getEOperationDocumentationKey(), rootAPIClassDoc);

		fluentAPIECls.getEAnnotations().add(anno);

		fluentAPIECls.setAbstract(false);
		fluentAPIECls.setInterface(false);
		fluentAPIECls.setName(FluentAPIRootAPIConstants.getFluentAPIRootAPIClassName());
		return fluentAPIECls;
	}

	private void addOperations(FluentAPIGenerationContext context) {
		context.getFluentAPIECls().getEOperations()
				.addAll(new FluentAPIRootAPICreateNewMethodGenerator().generateAllCreateNewMethods(context));

		context.getFluentAPIECls().getEOperations()
				.addAll(new FluentAPIRootAPINewMethodGenerator().getAllRootAPINewOperations(context));

		context.getFluentAPIECls().getEOperations().addAll(
				new FluentAPIRootAPIModifyElementMethodGenerator().getAllRootAPIModifyElementOperations(context));

		context.getFluentAPIECls().getEOperations()
				.addAll(new FluentAPIRootAPIContinueMethodGenerator().generateAllContinueMethods(context));

		context.getFluentAPIECls().getEOperations()
				.add(new FluentAPIRootAPIDropInitialisationMethodGenerator().generateDropInitialisationMethod(context));

		context.getFluentAPIECls().getEOperations()
				.addAll(new FluentAPIRootAPIMarkMethodGenerator().generateAllMarkMethods(context));

		context.getFluentAPIECls().getEOperations()
				.add(new FluentAPIRootAPIOnceExistsMethodGenerator().generateAllOnceExistsMethods(context));

		context.getFluentAPIECls().getEOperations().add(
				new FluentAPIRootAPIGetInitialisationForMethodGenerator().getInitialisationForEClassMethod(context));

		context.getFluentAPIECls().getEOperations().add(
				new FluentAPIRootAPIGetInitialisationForMethodGenerator().getInitialisationForClassMethod(context));

		context.getFluentAPIECls().getEOperations().add(
				new FluentAPIRootAPIGetInitialisationForMethodGenerator().getInitialisationForEObjectMethod(context));

		context.getFluentAPIECls().getEOperations()
				.addAll(new FluentAPIRootAPIWithOperationGenerator().getAllAPITopLevelWithOperations(context));

		context.getFluentAPIECls().getEOperations().add(new FluentAPIRootAPIGetAllSupportedClassesMethodGenerator()
				.generateGetAllSupportedClassesMethodGenerator());

		context.getFluentAPIECls().getEOperations()
				.add(new FluentAPIRootAPIGetOngoingInitsMethodGenerator().generateGetOngoingInitsMethod(context));

		context.getFluentAPIECls().getEOperations().add(new FluentAPIRootAPIClearAllOngoingInitialisationsMethodGenerator().generateClearAllOngoingInitsMethod(context));
	}

	public void setupRootAPIEClass(FluentAPIGenerationContext context) {
		addOperations(context);
	}
}
