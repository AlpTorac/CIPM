package cipm.consistency.fluentapi.gen.postprocessor;

import java.util.ArrayList;

import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPIGenerationForEachOverloadPostProcessor
		extends FluentAPIGenerationMultipleValueParameterPostProcessor {
	private static final Pattern methodNamePatternToOverload = Pattern.compile("(xW|w)ith(?=Removed|Added).*");
	private static final Pattern paramNamePatternToOverload = Pattern
			.compile("newFeatVal|featValToAdd|featValToRemove|featVal");

	private static final String iterationParamName = "e";
	/**
	 * The method body template for method overloads with collections / lists /
	 * arrays of feature values as parameters.
	 */
	private static final String multipleValueMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			"for (var " + iterationParamName + " : %s) this.%s(%s)",
			// %s: Overloaded parameter name
			// %s: Singular method name
			// %s: Serialised arguments
			"return this");

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
