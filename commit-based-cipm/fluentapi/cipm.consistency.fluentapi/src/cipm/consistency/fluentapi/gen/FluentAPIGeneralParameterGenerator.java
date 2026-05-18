package cipm.consistency.fluentapi.gen;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcorePackage;

/**
 * Utility class for generating recurring EParameter instances used in fluent
 * API generation.
 * 
 * @author Alp Torac Genc
 */
public final class FluentAPIGeneralParameterGenerator {
	public static EParameter getEObjectParamOfType(EClass type) {
		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(
				ModelConstants.GeneralParameters.USED_EOBJECT_PARAMETER_NAME.get(), type);
		FluentAPIGenerationUtil.addDocumentation(param,
				ModelConstants.GeneralParameters.USED_EOBJECT_PARAMETER_NAME_DOC.get());
		return param;
	}

	public static EParameter getEObjectParam() {
		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(
				ModelConstants.GeneralParameters.USED_EOBJECT_PARAMETER_NAME.get(), EcorePackage.Literals.EOBJECT);
		FluentAPIGenerationUtil.addDocumentation(param,
				ModelConstants.GeneralParameters.USED_EOBJECT_PARAMETER_NAME_DOC.get());
		return param;
	}

	public static EParameter getFeatParam() {
		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(
				ModelConstants.GeneralParameters.MODIFIED_FEATURE_PARAMETER_NAME.get(),
				EcorePackage.Literals.ESTRUCTURAL_FEATURE);
		FluentAPIGenerationUtil.addDocumentation(param,
				ModelConstants.GeneralParameters.MODIFIED_FEATURE_PARAMETER_NAME_DOC.get());
		return param;
	}

	public static EParameter getFeatValParam() {
		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(
				ModelConstants.GeneralParameters.FEATURE_VALUE_PARAMETER_NAME.get(),
				EcorePackage.Literals.EJAVA_OBJECT);
		FluentAPIGenerationUtil.addDocumentation(param,
				ModelConstants.GeneralParameters.FEATURE_VALUE_PARAMETER_NAME_DOC.get());
		return param;
	}

	public static EParameter getFeatValArrayParam(FluentAPIGenerationContext context) {
		var param = FluentAPIGenerationUtil.generateArrayValuedEParameter(context,
				ModelConstants.GeneralParameters.FEATURE_VALUE_PARAMETER_NAME.get(),
				EcorePackage.Literals.EJAVA_OBJECT);
		FluentAPIGenerationUtil.addDocumentation(param,
				ModelConstants.GeneralParameters.FEATURE_VALUE_PARAMETER_NAME_DOC.get());
		return param;
	}

	public static EParameter getFeatValColParam(FluentAPIGenerationContext context) {
		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(
				ModelConstants.GeneralParameters.FEATURE_VALUE_PARAMETER_NAME.get(),
				FluentAPIGenerationUtil.generateCollectionTypeParameter(context, null));
		FluentAPIGenerationUtil.addDocumentation(param,
				ModelConstants.GeneralParameters.FEATURE_VALUE_PARAMETER_NAME_DOC.get());
		return param;
	}

	public static EParameter getMarkKeyParam() {
		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(
				ModelConstants.GeneralParameters.MARK_KEY_PARAMETER_NAME.get(), EcorePackage.Literals.EJAVA_OBJECT);
		FluentAPIGenerationUtil.addDocumentation(param,
				ModelConstants.GeneralParameters.MARK_KEY_PARAMETER_NAME_DOC.get());
		return param;
	}

	public static EParameter getMarkKeyColParam(FluentAPIGenerationContext context) {
		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(
				ModelConstants.GeneralParameters.MARK_KEY_PARAMETER_NAME.get(),
				FluentAPIGenerationUtil.generateCollectionTypeParameter(context, null));
		FluentAPIGenerationUtil.addDocumentation(param,
				ModelConstants.GeneralParameters.MARK_KEY_PARAMETER_NAME_DOC.get());
		return param;
	}

	public static EParameter getMarkKeyArrayParam(FluentAPIGenerationContext context) {
		var param = FluentAPIGenerationUtil.generateArrayValuedEParameter(context,
				ModelConstants.GeneralParameters.MARK_KEY_PARAMETER_NAME.get(), EcorePackage.Literals.EJAVA_OBJECT);
		FluentAPIGenerationUtil.addDocumentation(param,
				ModelConstants.GeneralParameters.MARK_KEY_PARAMETER_NAME_DOC.get());
		return param;
	}

	public static EParameter getWaitForMarkTaskParam(FluentAPIGenerationContext context) {
		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(context,
				ModelConstants.GeneralParameters.WAIT_FOR_MARK_TASK_PARAMETER_NAME.get(),
				ModelConstants.GeneralParameters.WAIT_FOR_MARK_TASK_CLASS);
		FluentAPIGenerationUtil.addDocumentation(param,
				ModelConstants.GeneralParameters.WAIT_FOR_MARK_TASK_PARAMETER_NAME_DOC.get());
		return param;
	}

	public static EParameter getMarkValParam() {
		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(
				ModelConstants.GeneralParameters.MARK_VALUE_PARAMETER_NAME.get(), EcorePackage.Literals.EOBJECT);
		FluentAPIGenerationUtil.addDocumentation(param,
				ModelConstants.GeneralParameters.MARK_VALUE_PARAMETER_NAME_DOC.get());
		return param;
	}
}
