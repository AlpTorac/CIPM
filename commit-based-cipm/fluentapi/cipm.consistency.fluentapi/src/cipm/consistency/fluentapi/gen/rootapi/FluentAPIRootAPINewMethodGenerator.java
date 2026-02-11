package cipm.consistency.fluentapi.gen.rootapi;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationContext;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPIRootAPINewMethodGenerator {
	// TODO Add documentation

	private static final String newXMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Initialisation super type class name
			"return (%s)" + "this." + FluentAPIRootAPIConstants.getFluentAPIRootAPIGetInitialisationForMethodName()
					+ "(" + FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodEClassParameterName()
					+ ".getInstanceClass())");

	private static final String newXWithClassParamMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Initialisation super type class name
			"return (%s)" + "this." + FluentAPIRootAPIConstants.getFluentAPIRootAPIGetInitialisationForMethodName()
					+ "(" + FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodClassParameterName() + ")");

	private static final String newXWithModifiableFeatsMethodBodyTemplate = FluentAPIMethodsUtil
			.joinLOC("return (%s) this." + FluentAPIRootAPIConstants.getFluentAPIRootAPIGetInitialisationForMethodName()
					+ "(%s.class)");

	private static final String newXWithOnlyOneModifiableSingleValuedFeatsMethodBodyTemplate = FluentAPIMethodsUtil
			.joinLOC("return (%s) ((%s) this."
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIGetInitialisationForMethodName()
					+ "(%s.class)).with%s("
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodFeatureValueParameterName()
					+ ").createNow()");

	private static final String newXWithOnlyOneModifiableManyValuedFeatsMethodBodyTemplate_singleValue = FluentAPIMethodsUtil
			.joinLOC("return (%s) ((%s) this."
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIGetInitialisationForMethodName()
					+ "(%s.class)).withAdded%s("
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodFeatureValueParameterName()
					+ ").createNow()");
	private static final String newXWithOnlyOneModifiableManyValuedFeatsMethodBodyTemplate_multipleValues = newXWithOnlyOneModifiableManyValuedFeatsMethodBodyTemplate_singleValue;

	private static final String newXWithoutModifiableFeatsMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			"return (%s) ((%s) this." + FluentAPIRootAPIConstants.getFluentAPIRootAPIGetInitialisationForMethodName()
					+ "(%s.class)).createNow()");

	public List<EOperation> getAllRootAPINewOperations(FluentAPIGenerationContext context) {
		var eObjEClss = context.getTargetMetamodelPackageProvider().getAllTargetMetamodelConcreteEClasses();
		var ops = new ArrayList<EOperation>();
		ops.add(getRootAPITopLevelNewOperation(context));
		ops.add(getRootAPITopLevelNewOperationWithClassParameter(context));

		for (int i = 0; i < eObjEClss.size(); i++) {
			var eObjEClass = eObjEClss.get(i);
			var initEClass = context.getAllInitEClss().get(i);

			var modifiableFeatures = context.getTargetMetamodelFeatureFilter().getModifiableFeatures(eObjEClass);
			var modifiableFeatureCount = modifiableFeatures.size();

			if (modifiableFeatureCount == 0) {
				// Add direct creation methods, if there are no modifiable features
				ops.add(getRootAPINewOperationForEClassWithoutModifiableFeats(eObjEClass, initEClass));
			} else {
				// Add methods that lead to XInitialisation instances, if there are multiple
				// modifiable features
				ops.add(getRootAPINewOperationForEClassWithModifiableFeats(context, eObjEClass, initEClass));
			}

			// Add overloading method for types with only one modifiable feature to reduce
			// verbosity
			if (modifiableFeatureCount == 1) {
				ops.addAll(getRootAPINewOperationForEClassWithOnlyOneModifiableFeat(context, eObjEClass,
						modifiableFeatures.get(0), initEClass));
			}

		}

		return ops;
	}

	private EOperation getRootAPITopLevelNewOperation(FluentAPIGenerationContext context) {
		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodEClassParameterName(),
				EcorePackage.Literals.ECLASS);
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPINewXMethodName(), context.getInitSuperECls());
		FluentAPIGenerationUtil.addBody(op, String.format(newXMethodBodyTemplate,
				FluentAPIGenerationUtil.getFullyQualifiedEClassName(context.getInitSuperECls())));
		FluentAPIGenerationUtil.addEParameters(op, param);
		return op;
	}

	private EOperation getRootAPITopLevelNewOperationWithClassParameter(FluentAPIGenerationContext context) {
		var classParamType = FluentAPIGenerationUtil
				.generateEGenericTypeWithClassifier(EcorePackage.Literals.EJAVA_CLASS);
		FluentAPIGenerationUtil.addTypeArgument(classParamType, FluentAPIGenerationUtil.generateWildcardTypeArgument());
		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodClassParameterName(), classParamType);
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPINewXMethodName(), context.getInitSuperECls());
		FluentAPIGenerationUtil.addBody(op, String.format(newXWithClassParamMethodBodyTemplate,
				FluentAPIGenerationUtil.getFullyQualifiedEClassName(context.getInitSuperECls())));
		FluentAPIGenerationUtil.addEParameters(op, param);
		return op;
	}

	private EOperation getRootAPINewOperationForEClassWithModifiableFeats(FluentAPIGenerationContext context,
			EClass eObjEClass, EClass initECls) {
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodNameForType(eObjEClass), initECls);
		FluentAPIGenerationUtil.addBody(op,
				String.format(newXWithModifiableFeatsMethodBodyTemplate,
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
						eObjEClass.getInstanceClass().getName()));
		return op;
	}

	private List<EOperation> getRootAPINewOperationForEClassWithOnlyOneModifiableFeat(
			FluentAPIGenerationContext context, EClass eObjEClass, EStructuralFeature modifiableFeature,
			EClass initECls) {

		var ops = new ArrayList<EOperation>();

		if (modifiableFeature.isMany()) {
			// Many-valued features should also have a method that accepts one value (for
			// convenience)
			ops.addAll(getRootAPINewOperationForEClassWithOnlyOneModifiableManyValuedFeat_SingleValue(eObjEClass,
					modifiableFeature, initECls));
			ops.addAll(getRootAPINewOperationForEClassWithOnlyOneModifiableManyValuedFeat_MultipleValues(context,
					eObjEClass, modifiableFeature, initECls));
		} else {
			ops.addAll(getRootAPINewOperationForEClassWithOnlyOneModifiableSingleValuedFeat(eObjEClass,
					modifiableFeature, initECls));
		}

		return ops;
	}

	private List<EOperation> getRootAPINewOperationForEClassWithOnlyOneModifiableSingleValuedFeat(EClass eObjEClass,
			EStructuralFeature modifiableFeature, EClass initECls) {
		var ops = new ArrayList<EOperation>();

		// Extract and re-use the EOperation generation, since only the parameter type
		// is changed
		Function<EParameter, EOperation> opGenerator = (p) -> {
			var op = FluentAPIGenerationUtil.generateEOperation(
					FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodNameForType(eObjEClass), eObjEClass);
			FluentAPIGenerationUtil.addBody(op,
					String.format(newXWithOnlyOneModifiableSingleValuedFeatsMethodBodyTemplate,
							eObjEClass.getInstanceClass().getName(),
							FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
							eObjEClass.getInstanceClass().getName(),
							StringUtils.capitalize(modifiableFeature.getName())));
			FluentAPIGenerationUtil.addEParameters(op, p);
			return op;
		};

		var originalOpFeatureValParam = getSingleValuedFeatValParam(modifiableFeature);
		var originalOp = opGenerator.apply(originalOpFeatureValParam);
		ops.add(originalOp);

		if (originalOpFeatureValParam.getEType().equals(EcorePackage.Literals.EBIG_INTEGER)) {
			var longOpNewFeatValParam = getSingleValuedFeatValParam(modifiableFeature);
			longOpNewFeatValParam.setEType(EcorePackage.Literals.ELONG);
			var longOp = opGenerator.apply(longOpNewFeatValParam);
			ops.add(longOp);

			var intOpNewFeatValParam = getSingleValuedFeatValParam(modifiableFeature);
			intOpNewFeatValParam.setEType(EcorePackage.Literals.EINT);
			var intOp = opGenerator.apply(intOpNewFeatValParam);
			ops.add(intOp);
		}
		return ops;
	}

	private List<EOperation> getRootAPINewOperationForEClassWithOnlyOneModifiableManyValuedFeat_SingleValue(
			EClass eObjEClass, EStructuralFeature modifiableFeature, EClass initECls) {
		var ops = new ArrayList<EOperation>();

		var featureValParam = getSingleValuedFeatValParam(modifiableFeature);
		var listOp = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodNameForType(eObjEClass), eObjEClass);
		FluentAPIGenerationUtil.addBody(listOp,
				String.format(newXWithOnlyOneModifiableManyValuedFeatsMethodBodyTemplate_singleValue,
						eObjEClass.getInstanceClass().getName(),
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
						eObjEClass.getInstanceClass().getName(), StringUtils.capitalize(modifiableFeature.getName())));
		FluentAPIGenerationUtil.addEParameters(listOp, featureValParam);
		ops.add(listOp);

		return ops;
	}

	private List<EOperation> getRootAPINewOperationForEClassWithOnlyOneModifiableManyValuedFeat_MultipleValues(
			FluentAPIGenerationContext context, EClass eObjEClass, EStructuralFeature modifiableFeature,
			EClass initECls) {
		var ops = new ArrayList<EOperation>();

		// Extract and re-use the EOperation generation, since only the parameter type
		// is changed
		Function<EParameter, EOperation> opGenerator = (p) -> {
			var op = FluentAPIGenerationUtil.generateEOperation(
					FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodNameForType(eObjEClass), eObjEClass);
			FluentAPIGenerationUtil.addBody(op,
					String.format(newXWithOnlyOneModifiableManyValuedFeatsMethodBodyTemplate_multipleValues,
							eObjEClass.getInstanceClass().getName(),
							FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
							eObjEClass.getInstanceClass().getName(),
							StringUtils.capitalize(modifiableFeature.getName())));
			FluentAPIGenerationUtil.addEParameters(op, p);
			return op;
		};

		var listFeatureValParam = getColFeatValParam(context, modifiableFeature);
		var listOp = opGenerator.apply(listFeatureValParam);
		ops.add(listOp);

		var arrayFeatureValParam = getArrayFeatValParam(context, modifiableFeature);
		var arrayOp = opGenerator.apply(arrayFeatureValParam);
		ops.add(arrayOp);

		return ops;
	}

	private EOperation getRootAPINewOperationForEClassWithoutModifiableFeats(EClass eObjEClass, EClass initECls) {
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodNameForType(eObjEClass), eObjEClass);
		FluentAPIGenerationUtil.addBody(op,
				String.format(newXWithoutModifiableFeatsMethodBodyTemplate, eObjEClass.getInstanceClass().getName(),
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
						eObjEClass.getInstanceClass().getName()));
		return op;
	}

	private EParameter getSingleValuedFeatValParam(EStructuralFeature feat) {
		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodFeatureValueParameterName(), feat.getEType());
		// TODO Add documentation
		return param;
	}

	private EParameter getColFeatValParam(FluentAPIGenerationContext context, EStructuralFeature feat) {
		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodFeatureValueParameterName(),
				FluentAPIGenerationUtil.generateCollectionTypeParameter(context, feat.getEType()));
		// TODO Add documentation
		return param;
	}

	private EParameter getArrayFeatValParam(FluentAPIGenerationContext context, EStructuralFeature feat) {
		var param = FluentAPIGenerationUtil.generateArrayValuedEParameter(context,
				FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodFeatureValueParameterName(), feat.getEType());
		// TODO Add documentation
		return param;
	}
}
