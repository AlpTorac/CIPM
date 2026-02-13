package cipm.consistency.fluentapi.gen.init;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.FluentAPIGeneralParameterGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationContext;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.IFluentAPIMethodGenerator;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.mark.FluentAPIOnceExistsExtension;
import cipm.consistency.fluentapi.gen.rootapi.FluentAPIRootAPIConstants;

public class FluentAPIInitialisationOnceExistsMethodGenerator implements IFluentAPIMethodGenerator {
	private static final String onceExistsMethodMarkedKeyBodyTemplate =
			// %s: Mark key parameter name
			// %s: Model construction task parameter name
			FluentAPIMethodsUtil.joinLOC(
					FluentAPIOnceExistsExtension.class.getName() + ".addOnceExists("
							+ FluentAPIGeneralParameterGenerator.getFluentAPIMarkKeyParameterName() + ", "
							+ FluentAPIGeneralParameterGenerator.getFluentAPIOnceExistsRunnableParameterName() + ")",
					"return this");

	public List<EOperation> generateAllOnceExistsMethods(FluentAPIGenerationContext context, EClass initECls) {
		var ops = new ArrayList<EOperation>();
		ops.add(generateOnceExistsMethodForSingleKey(context, initECls));
		return ops;
	}

	private EOperation generateOnceExistsMethodForSingleKey(FluentAPIGenerationContext context, EClass initECls) {
		var keyParam = FluentAPIGeneralParameterGenerator.getMarkKeyParam();
		var taskParam = FluentAPIGeneralParameterGenerator.getRunnableParam(context);

		var op = FluentAPIGenerationUtil
				.generateEOperation(FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMethodName(), initECls);
		FluentAPIGenerationUtil.addEParameters(op, keyParam, taskParam);
		FluentAPIGenerationUtil.addBody(op, onceExistsMethodMarkedKeyBodyTemplate);
		FluentAPIGenerationUtil.addDocumentation(op,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMethodDocumentation());
		return op;
	}

	@Override
	public Map<String, String> getMethodNamesToDescriptions() {
		return Map.of(FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMethodName(),
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMethodSummary());
	}
}
