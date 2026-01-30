package cipm.consistency.fluentapi.gen.superinit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.IFluentAPIMethodGenerator;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.mark.FluentAPIOnceExistsExtension;
import cipm.consistency.fluentapi.gen.rootapi.FluentAPIRootAPIConstants;

public class FluentAPISuperInitialisationOnceExistsMethodGenerator implements IFluentAPIMethodGenerator {

	private static final String onceExistsMethodSingleMarkedKeyBody = FluentAPIMethodsUtil
			.joinLOC(
					FluentAPIOnceExistsExtension.class.getName() + ".addOnceExists("
							+ FluentAPISuperInitialisationConstants
									.getFluentAPISuperInitialisationMarkKeyParameterName()
							+ ", (" + FluentAPIRootAPIConstants.getModelConstructionTaskClass().getName() + ") "
							+ FluentAPISuperInitialisationConstants
									.getFluentAPISuperInitialisationOnceExistsRunnableParameterName()
							+ ")",
					"return this");

	private static final String onceExistsMethodMultipleMarkedKeyBody = FluentAPIMethodsUtil
			.joinLOC(
					FluentAPIOnceExistsExtension.class.getName() + ".addOnceExists("
							+ FluentAPISuperInitialisationConstants
									.getFluentAPISuperInitialisationOnceExistsMarkKeyListParameterName()
							+ ", (" + FluentAPIRootAPIConstants.getModelConstructionTaskClass().getName() + ") "
							+ FluentAPISuperInitialisationConstants
									.getFluentAPISuperInitialisationOnceExistsRunnableParameterName()
							+ ")",
					"return this");

	public List<EOperation> generateAllOnceExistsMethods(EClass superInitECls) {
		var ops = new ArrayList<EOperation>();
		ops.add(generateOnceExistsMethod(superInitECls));
		ops.add(generateOnceExistsListMethod(superInitECls));
		ops.add(generateOnceExistsArrayMethod(superInitECls));
		return ops;
	}

	private EOperation generateOnceExistsMethod(EClass superInitECls) {
		var keyParam = getMarkKeyParam();
		var taskParam = getTaskParam();
		var op = FluentAPIGenerationUtil
				.generateEOperation(FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMethodName(), superInitECls);
		FluentAPIGenerationUtil.addBody(op, onceExistsMethodSingleMarkedKeyBody);
		FluentAPIGenerationUtil.addEParameters(op, keyParam, taskParam);
		FluentAPIGenerationUtil.addDocumentation(op,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMethodDocumentation());
		return op;
	}

	private EOperation generateOnceExistsListMethod(EClass superInitECls) {
		var keyParam = getMarkKeyListParam();
		var taskParam = getTaskParam();
		var op = FluentAPIGenerationUtil
				.generateEOperation(FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMethodName(), superInitECls);
		FluentAPIGenerationUtil.addBody(op, onceExistsMethodMultipleMarkedKeyBody);
		FluentAPIGenerationUtil.addEParameters(op, keyParam, taskParam);
		FluentAPIGenerationUtil.addDocumentation(op,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMethodDocumentation());
		return op;
	}

	private EOperation generateOnceExistsArrayMethod(EClass superInitECls) {
		var keyParam = getMarkKeyArrayParam();
		var taskParam = getTaskParam();
		var op = FluentAPIGenerationUtil
				.generateEOperation(FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMethodName(), superInitECls);
		FluentAPIGenerationUtil.addBody(op, onceExistsMethodMultipleMarkedKeyBody);
		FluentAPIGenerationUtil.addEParameters(op, keyParam, taskParam);
		FluentAPIGenerationUtil.addDocumentation(op,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMethodDocumentation());
		return op;
	}

	private EParameter getMarkKeyParam() {
		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIMarkKeyParameterName(),
				EcorePackage.Literals.EJAVA_OBJECT);
		FluentAPIGenerationUtil.addDocumentation(param,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMarkKeyDocumentation());
		return param;
	}

	private EParameter getMarkKeyListParam() {
		var param = FluentAPIGenerationUtil.generateManyValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMarkKeyListParameterName(),
				EcorePackage.Literals.EJAVA_OBJECT);
		FluentAPIGenerationUtil.addDocumentation(param,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMarkKeyDocumentation());
		return param;
	}

	private EParameter getMarkKeyArrayParam() {
		var param = FluentAPIGenerationUtil.generateArrayValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMarkKeyListParameterName(),
				EcorePackage.Literals.EJAVA_OBJECT);
		FluentAPIGenerationUtil.addDocumentation(param,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMarkKeyDocumentation());
		return param;
	}

	private EParameter getTaskParam() {
		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsRunnableParameterName(),
				FluentAPIRootAPIConstants.getModelConstructionTaskClass());
		FluentAPIGenerationUtil.addDocumentation(param,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsRunnableParameterDocumentation());
		return param;
	}

	@Override
	public Map<String, String> getMethodNamesToDescriptions() {
		return Map.of(FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMethodName(),
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMethodSummary());
	}
}
