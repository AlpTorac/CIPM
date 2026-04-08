package cipm.consistency.fluentapi.gen.rootapi;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcoreFactory;

import cipm.consistency.fluentapi.gen.FluentAPIDocumentationUtil;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationContext;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.ModelConstants;

public class FluentAPIRootAPIEClassGenerator {
	// TODO Add documentation

	private static final Map<String, String> summaries = new LinkedHashMap<>();

	public EClass generateRootAPIEClass() {
		var fluentAPIECls = EcoreFactory.eINSTANCE.createEClass();
		fluentAPIECls.setAbstract(false);
		fluentAPIECls.setInterface(false);
		fluentAPIECls.setName(ModelConstants.FluentAPI.CLASS_NAME.get());
		return fluentAPIECls;
	}

	private void addEClassDoc(FluentAPIGenerationContext context) {
		var doc = ModelConstants.FluentAPI.CLASS_DOC.getFor(FluentAPIDocumentationUtil.serialiseSummaries(summaries));
		FluentAPIGenerationUtil.addDocumentation(context.getFluentAPIECls(), doc);
	}

	private void addOperations(FluentAPIGenerationContext context) {
		var createNewMetGen = new FluentAPIRootAPICreateNewMethodGenerator();
		context.getFluentAPIECls().getEOperations().addAll(createNewMetGen.generateAllCreateNewMethods(context));
		summaries.putAll(createNewMetGen.getMethodNamesToDescriptions());

		var newMetGen = new FluentAPIRootAPINewMethodGenerator();
		context.getFluentAPIECls().getEOperations().addAll(newMetGen.getAllRootAPINewOperations(context));
		summaries.putAll(newMetGen.getMethodNamesToDescriptions());

		var modElemMetGen = new FluentAPIRootAPIModifyElementMethodGenerator();
		context.getFluentAPIECls().getEOperations().addAll(modElemMetGen.getAllRootAPIModifyElementOperations(context));
		summaries.putAll(modElemMetGen.getMethodNamesToDescriptions());

		var conMetGen = new FluentAPIRootAPIContinueMethodGenerator();
		context.getFluentAPIECls().getEOperations().addAll(conMetGen.generateAllContinueMethods(context));
		summaries.putAll(conMetGen.getMethodNamesToDescriptions());

		var dropInitMetGen = new FluentAPIRootAPIDropInitialisationMethodGenerator();
		context.getFluentAPIECls().getEOperations().add(dropInitMetGen.generateDropInitialisationMethod(context));
		summaries.putAll(dropInitMetGen.getMethodNamesToDescriptions());

		var markMetGen = new FluentAPIRootAPIMarkMethodGenerator();
		context.getFluentAPIECls().getEOperations().addAll(markMetGen.generateAllMarkMethods(context));
		summaries.putAll(markMetGen.getMethodNamesToDescriptions());

		var onceExtGen = new FluentAPIRootAPIOnceExistsMethodGenerator();
		context.getFluentAPIECls().getEOperations().add(onceExtGen.generateAllOnceExistsMethods(context));
		summaries.putAll(onceExtGen.getMethodNamesToDescriptions());

		var getInitMetGen = new FluentAPIRootAPIGetInitialisationForMethodGenerator();
		context.getFluentAPIECls().getEOperations().addAll(
				new FluentAPIRootAPIGetInitialisationForMethodGenerator().getAllInitialisationForMethods(context));
		summaries.putAll(getInitMetGen.getMethodNamesToDescriptions());

		var withOpMetGen = new FluentAPIRootAPIWithOperationGenerator();
		context.getFluentAPIECls().getEOperations()
				.addAll(new FluentAPIRootAPIWithOperationGenerator().getAllAPITopLevelWithOperations(context));
		summaries.putAll(withOpMetGen.getMethodNamesToDescriptions());

		var getAllSupClsMetGen = new FluentAPIRootAPIGetAllSupportedClassesMethodGenerator();
		context.getFluentAPIECls().getEOperations().add(new FluentAPIRootAPIGetAllSupportedClassesMethodGenerator()
				.generateGetAllSupportedClassesMethodGenerator());
		summaries.putAll(getAllSupClsMetGen.getMethodNamesToDescriptions());

		var getOngInitMetGen = new FluentAPIRootAPIGetOngoingInitsMethodGenerator();
		context.getFluentAPIECls().getEOperations()
				.add(new FluentAPIRootAPIGetOngoingInitsMethodGenerator().generateGetOngoingInitsMethod(context));
		summaries.putAll(getOngInitMetGen.getMethodNamesToDescriptions());

		var clrOngInitMetGen = new FluentAPIRootAPIClearAllOngoingInitialisationsMethodGenerator();
		context.getFluentAPIECls().getEOperations()
				.add(new FluentAPIRootAPIClearAllOngoingInitialisationsMethodGenerator()
						.generateClearAllOngoingInitsMethod(context));
		summaries.putAll(clrOngInitMetGen.getMethodNamesToDescriptions());
	}

	public void setupRootAPIEClass(FluentAPIGenerationContext context) {
		addOperations(context);
		addEClassDoc(context);
	}
}
