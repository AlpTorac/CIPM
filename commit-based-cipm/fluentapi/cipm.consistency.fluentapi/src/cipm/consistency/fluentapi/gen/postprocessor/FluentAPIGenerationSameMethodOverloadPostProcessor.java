package cipm.consistency.fluentapi.gen.postprocessor;

import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.ModelConstants;

public class FluentAPIGenerationSameMethodOverloadPostProcessor
		extends FluentAPIGenerationMultipleValueParameterPostProcessor {
	// new(?!X|Element).*
	private static final Pattern newMethodPatternToSkip = Pattern.compile(String.join("|",
			ModelConstants.FluentAPI.New.TOP_NAME.get(), ModelConstants.SuperInitialisation.NewElement.NAME.get()));

	private static final Pattern newMethodPatternToOverload = Pattern
			.compile(ModelConstants.FluentAPI.New.NAME.getFor(".*"));

	private static final Pattern onceExistsMethodPatternToOverload = Pattern
			.compile(ModelConstants.FluentAPI.OnceExists.NAME.get());

	private static final Pattern paramNamePatternToOverload = Pattern
			.compile(String.join("|", ModelConstants.GeneralParameters.FEATURE_VALUE_PARAMETER_NAME.get(),
					ModelConstants.GeneralParameters.MARK_KEY_PARAMETER_NAME.get()));

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
		return ((!newMethodPatternToSkip.matcher(op.getName()).matches()
				&& newMethodPatternToOverload.matcher(op.getName()).matches()
				&& op.getEAnnotations().get(0).getDetails().get(FluentAPIGenerationUtil.getEOperationBodyKey())
						.contains("." + ModelConstants.Initialiation.WithAdded.NAME.getEmpty()))
				|| (onceExistsMethodPatternToOverload.matcher(op.getName()).matches()))
				&& op.getEParameters().stream().anyMatch(this::shouldOverloadParameter);
	}
}
