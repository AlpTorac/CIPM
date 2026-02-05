package cipm.consistency.fluentapi.gen;

import java.util.Collection;

import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcorePackage;

public final class FluentAPIGeneralParameterGenerator {
	private static final String fluentAPIEObjectParameterName = "eobj";
	private static final String fluentAPIEObjectParameterDocumentation = "The EObject that this method will use";

	private static final String fluentAPIFeatureParameterName = "featToModify";
	private static final String fluentAPIFeatureParameterDocumentation = "The feature, whose value in "
			+ fluentAPIFeatureParameterName + " will be modified";

	private static final String fluentAPIFeatureValueParameterName = "featVal";
	private static final String fluentAPIFeatureValueParameterDocumentation = "The value of the feature, which will be used to modify the given feature in certain ways, denoted by the method name";

	private static final String fluentAPIMarkValParameterName = "markVal";
	private static final String fluentAPIMarkValParameterDocumentation = "The object that is / will be marked.";

	private static final String fluentAPIMarkKeyParameterName = "markKey";
	private static final String fluentAPIMarkKeyDocumentation = "The object instance (key), whose memory address is serving / will serve as a key in mark-related operations. Note that the contents of the key are fully irrelevant here, only its memory address matters.";

	private static final Class<?> fluentAPIOnceExistsRunnableParameterType = Runnable.class;
	private static final String fluentAPIOnceExistsRunnableParameterName = "toDoOnceExists";
	private static final String fluentAPIOnceExistsRunnableParameterDocumentation = "The model construction task, which will be executed upon object(s) getting marked with certain "
			+ fluentAPIMarkKeyParameterName + "(s).";

	public static EParameter getEObjectParam() {
		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(fluentAPIEObjectParameterName,
				EcorePackage.Literals.EOBJECT);
		FluentAPIGenerationUtil.addDocumentation(param, fluentAPIEObjectParameterDocumentation);
		return param;
	}

	public static EParameter getFeatParam() {
		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(fluentAPIFeatureParameterName,
				EcorePackage.Literals.ESTRUCTURAL_FEATURE);
		FluentAPIGenerationUtil.addDocumentation(param, fluentAPIFeatureParameterDocumentation);
		return param;
	}

	public static EParameter getFeatValParam() {
		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(fluentAPIFeatureValueParameterName,
				EcorePackage.Literals.EJAVA_OBJECT);
		FluentAPIGenerationUtil.addDocumentation(param, fluentAPIFeatureValueParameterDocumentation);
		return param;
	}

	public static EParameter getFeatValArrayParam(FluentAPIGenerationContext context) {
		var param = FluentAPIGenerationUtil.generateArrayValuedEParameter(context, fluentAPIFeatureValueParameterName,
				EcorePackage.Literals.EJAVA_OBJECT);
		FluentAPIGenerationUtil.addDocumentation(param, fluentAPIFeatureValueParameterDocumentation);
		return param;
	}

	public static EParameter getFeatValColParam(FluentAPIGenerationContext context) {
		var pureColType = FluentAPIGenerationUtil.createOrGetEDataType(context, Collection.class, 1);
		var colGenTypeArgument = FluentAPIGenerationUtil.generateEGenericTypeWithBounds(null, null);
		var colGenType = FluentAPIGenerationUtil.generateEGenericTypeWithClassifier(pureColType);
		FluentAPIGenerationUtil.addTypeArgument(colGenType, colGenTypeArgument);

		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(fluentAPIFeatureValueParameterName,
				colGenType);
		FluentAPIGenerationUtil.addDocumentation(param, fluentAPIFeatureValueParameterDocumentation);
		return param;
	}

	public static EParameter getMarkKeyParam() {
		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(fluentAPIMarkKeyParameterName,
				EcorePackage.Literals.EJAVA_OBJECT);
		FluentAPIGenerationUtil.addDocumentation(param, fluentAPIMarkKeyDocumentation);
		return param;
	}

	public static EParameter getMarkKeyListParam() {
		var param = FluentAPIGenerationUtil.generateManyValuedEParameter(fluentAPIMarkKeyParameterName,
				EcorePackage.Literals.EJAVA_OBJECT);
		FluentAPIGenerationUtil.addDocumentation(param, fluentAPIMarkKeyDocumentation);
		return param;
	}

	public static EParameter getMarkKeyArrayParam(FluentAPIGenerationContext context) {
		var param = FluentAPIGenerationUtil.generateArrayValuedEParameter(context, fluentAPIMarkKeyParameterName,
				EcorePackage.Literals.EJAVA_OBJECT);
		FluentAPIGenerationUtil.addDocumentation(param, fluentAPIMarkKeyDocumentation);
		return param;
	}

	public static EParameter getRunnableParam(FluentAPIGenerationContext context) {
		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(context,
				fluentAPIOnceExistsRunnableParameterName, fluentAPIOnceExistsRunnableParameterType);
		FluentAPIGenerationUtil.addDocumentation(param, fluentAPIOnceExistsRunnableParameterDocumentation);
		return param;
	}

	public static EParameter getMarkValParam() {
		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(fluentAPIMarkValParameterName,
				EcorePackage.Literals.EOBJECT);
		FluentAPIGenerationUtil.addDocumentation(param, fluentAPIMarkValParameterDocumentation);
		return param;
	}

	public static String getFluentAPIEObjectParameterName() {
		return fluentAPIEObjectParameterName;
	}

	public static String getFluentAPIFeatureParameterName() {
		return fluentAPIFeatureParameterName;
	}

	public static String getFluentAPIFeatureValueParameterName() {
		return fluentAPIFeatureValueParameterName;
	}

	public static String getFluentAPIMarkValParameterName() {
		return fluentAPIMarkValParameterName;
	}

	public static String getFluentAPIMarkKeyParameterName() {
		return fluentAPIMarkKeyParameterName;
	}

	public static String getFluentAPIOnceExistsRunnableParameterName() {
		return fluentAPIOnceExistsRunnableParameterName;
	}
}
