package cipm.consistency.fluentapi.gen.postprocessor;

import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;

public class FluentAPIGenerationSameMethodOverloadPostProcessor
		extends FluentAPIGenerationMultipleValueParameterPostProcessor {
	private static final Pattern newMethodPatternToOverload = Pattern.compile("(new(?!X|Element).*)");
	private static final Pattern onceExistsMethodPatternToOverload = Pattern.compile("onceExists");

	private static final Pattern paramNamePatternToOverload = Pattern.compile("featVal|markKey");

	@Override
	protected EOperation overloadMethodBody(EOperation overloadingOp, EParameter newParam) {
		return overloadingOp;
	}

	@Override
	protected boolean shouldOverloadParameter(EParameter param) {
		return paramNamePatternToOverload.matcher(param.getName()).matches();
	}

	@Override
	protected boolean shouldOverloadMethod(EOperation op) {
		return ((newMethodPatternToOverload.matcher(op.getName()).matches() && op.getEAnnotations().get(0).getDetails()
				.get(FluentAPIGenerationUtil.getEOperationBodyKey()).contains(".withAdded"))
				|| (onceExistsMethodPatternToOverload.matcher(op.getName()).matches()))
				&& op.getEParameters().stream().anyMatch(this::shouldOverloadParameter);
	}
}
