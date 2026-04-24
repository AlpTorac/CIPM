package cipm.consistency.fluentapi.postprocessor;

import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationContext;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.ModelConstants;

/**
 * Introduces variants for certain methods that consider singular parameters,
 * for convenience. These variants have the same method body as the original
 * method and take Collections or Arrays as parameters.
 * <p>
 * <p>
 * Note: Even though the variants have the same method body with the original
 * method, they may result in different behaviour, if method calls within their
 * method body are overloaded.
 * 
 * @author Alp Torac Genc
 */
public class FluentAPIGenerationMultipleValueParameterSameMethodBodyOverloadPostProcessor
		extends FluentAPIGenerationMultipleValueParameterPostProcessor {
	private static final Pattern newMethodPatternToSkip = Pattern.compile(String.join("|",
			ModelConstants.FluentAPI.New.TOP_NAME.get(), ModelConstants.SuperInitialisation.NewElement.NAME.get()));

	private static final Pattern newMethodPatternToOverload = Pattern
			.compile(ModelConstants.FluentAPI.New.NAME.getFor(".*"));

	private static final Pattern onceExistsMethodPatternToOverload = Pattern
			.compile(ModelConstants.FluentAPI.OnceExists.NAME.get());

	private static final Pattern paramNamePatternToOverload = Pattern
			.compile(String.join("|", ModelConstants.GeneralParameters.FEATURE_VALUE_PARAMETER_NAME.get(),
					ModelConstants.GeneralParameters.MARK_KEY_PARAMETER_NAME.get()));

	public FluentAPIGenerationMultipleValueParameterSameMethodBodyOverloadPostProcessor(
			FluentAPIGenerationContext context, List<EClass> eClsScope) {
		super(context, eClsScope);
	}

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
