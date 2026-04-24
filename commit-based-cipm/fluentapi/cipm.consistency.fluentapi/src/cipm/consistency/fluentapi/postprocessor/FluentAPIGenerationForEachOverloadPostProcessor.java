package cipm.consistency.fluentapi.postprocessor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationContext;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.ModelConstants;
import cipm.consistency.fluentapi.methods.FluentAPIMethodsUtil;

/**
 * Introduces variants for certain methods that consider singular parameters,
 * for convenience. The introduced variants envelop the original method in a
 * for-each loop and take Collections or Arrays as parameters.
 * 
 * @author Alp Torac Genc
 */
public class FluentAPIGenerationForEachOverloadPostProcessor
		extends FluentAPIGenerationMultipleValueParameterPostProcessor {

	private static final Pattern methodNamePatternToOverload = Pattern.compile(String.join("|",
			ModelConstants.FluentAPI.WithAddedFeat.NAME.get(), ModelConstants.FluentAPI.WithRemovedFeat.NAME.get(),
			ModelConstants.Initialiation.WithAdded.NAME.getFor(".*"),
			ModelConstants.Initialiation.WithRemoved.NAME.getFor(".*")));

	private static final Pattern paramNamePatternToOverload = Pattern
			.compile(String.join("|", ModelConstants.GeneralParameters.FEATURE_VALUE_PARAMETER_NAME.get(),
					ModelConstants.Initialiation.With.PARAMETER_NAME.get(),
					ModelConstants.Initialiation.WithAdded.PARAMETER_NAME.get(),
					ModelConstants.Initialiation.WithRemoved.PARAMETER_NAME.get()));

	private static final String iterationParamName = "e";
	/**
	 * The method body template for method overloads with collections / lists /
	 * arrays of feature values as parameters.
	 */
	private static final String multipleValueMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			"for (var " + iterationParamName + " : %s) this.%s(%s)",
			// %s: Overloaded parameter name
			// %s: Original method name
			// %s: Serialised arguments
			"return this");

	public FluentAPIGenerationForEachOverloadPostProcessor(FluentAPIGenerationContext context, List<EClass> eClsScope) {
		super(context, eClsScope);
	}

	@Override
	protected EOperation overloadMethodBody(EOperation overloadingOp, EParameter newParam) {
		var serialisedArguments = new ArrayList<String>();
		for (var p : overloadingOp.getEParameters()) {
			if (p != newParam) {
				serialisedArguments.add(p.getName());
			} else {
				serialisedArguments.add(iterationParamName);
			}
		}
		FluentAPIGenerationUtil.addBody(overloadingOp,
				String.format(multipleValueMethodBodyTemplate, newParam.getName(), overloadingOp.getName(),
						String.join(",", serialisedArguments.toArray(String[]::new))));
		return overloadingOp;
	}

	@Override
	protected boolean shouldOverloadParameter(EParameter param) {
		return paramNamePatternToOverload.matcher(param.getName()).matches();
	}

	@Override
	protected boolean shouldOverloadMethod(EOperation op) {
		return methodNamePatternToOverload.matcher(op.getName()).matches()
				&& op.getEParameters().stream().anyMatch(this::shouldOverloadParameter)
				&& op.getEType().equals(op.getEContainingClass());
	}
}
