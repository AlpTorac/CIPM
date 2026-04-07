package cipm.consistency.fluentapi.gen.rootapi;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcoreFactory;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationContext;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.ModelConstants;

public class FluentAPIRootAPIEClassGenerator {
	// TODO Add documentation
	// TODO Re-use / link to documentations of mentioned API classes

	public EClass generateRootAPIEClass() {
		var fluentAPIECls = EcoreFactory.eINSTANCE.createEClass();

		var anno = EcoreFactory.eINSTANCE.createEAnnotation();
		anno.setSource(ModelConstants.GEN_MODEL_SOURCE_URL.get());
		anno.getDetails().put(FluentAPIGenerationUtil.getEOperationDocumentationKey(),
				ModelConstants.FluentAPI.CLASS_DOC.get());

		fluentAPIECls.getEAnnotations().add(anno);

		fluentAPIECls.setAbstract(false);
		fluentAPIECls.setInterface(false);
		fluentAPIECls.setName(ModelConstants.FluentAPI.CLASS_NAME.get());
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

		context.getFluentAPIECls().getEOperations()
				.add(new FluentAPIRootAPIClearAllOngoingInitialisationsMethodGenerator()
						.generateClearAllOngoingInitsMethod(context));
	}

	public void setupRootAPIEClass(FluentAPIGenerationContext context) {
		addOperations(context);
	}
}
