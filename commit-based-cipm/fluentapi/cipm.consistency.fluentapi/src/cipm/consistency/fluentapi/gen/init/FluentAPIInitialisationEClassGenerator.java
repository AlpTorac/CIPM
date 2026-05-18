package cipm.consistency.fluentapi.gen.init;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcoreFactory;

import cipm.consistency.fluentapi.gen.FluentAPIDocumentationUtil;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationContext;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.ModelConstants;
import cipm.consistency.fluentapi.gen.superinit.FluentAPISuperInitialisationGetInitialisedEClassMethodGenerator;
import cipm.consistency.fluentapi.gen.superinit.FluentAPISuperInitialisationResetOperationGenerator;
import cipm.consistency.fluentapi.gen.superinit.FluentAPISuperInitialisationToAPIMethodGenerator;

/**
 * Generates EClass instances for concrete initialisation classes.
 * 
 * @author Alp Torac Genc
 */
public class FluentAPIInitialisationEClassGenerator {
	private static final Map<String, String> summaries = new LinkedHashMap<>();

	public List<EClass> generateFluentAPIInitialisationClasses(FluentAPIGenerationContext context) {
		var initSubClss = new ArrayList<EClass>();

		for (var initialisedEClass : context.getTargetMetamodelPackageProvider()
				.getAllTargetMetamodelConcreteEClasses()) {
			var initSubCls = generateInitialisationEClass(initialisedEClass, context);
			context.addInitECls(initialisedEClass, initSubCls);
			initSubClss.add(initSubCls);
		}

		return initSubClss;
	}

	private void addXInitEClassDocumentation(EClass xInitEClass, EClass initialisedEClass,
			FluentAPIGenerationContext context) {

		var doc = ModelConstants.Initialiation.CLASS_DOC.getFor(initialisedEClass.getName(),
				context.getTargetMetamodelPackageProvider().getTargetMetamodelName(), initialisedEClass.getName(),
				FluentAPIDocumentationUtil.serialiseSummaries(summaries));
		FluentAPIGenerationUtil.addDocumentation(xInitEClass, doc);
	}

	private EClass generateInitialisationEClass(EClass initialisedEClass, FluentAPIGenerationContext context) {
		var xInitEClass = EcoreFactory.eINSTANCE.createEClass();
		xInitEClass.setName(
				ModelConstants.Initialiation.CLASS_NAME.getFor(StringUtils.capitalize(initialisedEClass.getName())));
		return xInitEClass;
	}

	public void setupFluentAPIInitialisationFor(EClass xInitEClass, EClass initialisedEClass,
			FluentAPIGenerationContext context) {
		xInitEClass.getEOperations().addAll(new FluentAPIInitialisationReturnTypeOverrideGenerator()
				.generateMethodsWithOverridingReturnType(context, xInitEClass, initialisedEClass));

		addNonOverriddenInheritedMethodSummaries();
		addOperations(xInitEClass, initialisedEClass, context);
		addXInitEClassDocumentation(xInitEClass, initialisedEClass, context);
	}

	private void addNonOverriddenInheritedMethodSummaries() {
		var getInitEClsGen = new FluentAPISuperInitialisationGetInitialisedEClassMethodGenerator();
		summaries.putAll(getInitEClsGen.getMethodNamesToDescriptions());

		var resetGen = new FluentAPISuperInitialisationResetOperationGenerator();
		summaries.putAll(resetGen.getMethodNamesToDescriptions());

		var toAPIGen = new FluentAPISuperInitialisationToAPIMethodGenerator();
		summaries.putAll(toAPIGen.getMethodNamesToDescriptions());
	}

	private void addOperations(EClass xInitEClass, EClass initialisedEClass, FluentAPIGenerationContext context) {
		var newElementGen = new FluentAPIInitialisationNewElementOperationGenerator();
		xInitEClass.getEOperations().add(newElementGen.getNewElementOperationFor(xInitEClass, initialisedEClass));
		summaries.putAll(newElementGen.getMethodNamesToDescriptions());

		var withGen = new FluentAPIInitialisationWithOperationGenerator();
		xInitEClass.getEOperations()
				.addAll(withGen.generateAllWithOperationsFor(context, xInitEClass, initialisedEClass));
		summaries.putAll(withGen.getMethodNamesToDescriptions());
	}
}
