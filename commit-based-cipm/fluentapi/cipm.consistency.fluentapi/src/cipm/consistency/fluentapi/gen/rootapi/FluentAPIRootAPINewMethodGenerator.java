package cipm.consistency.fluentapi.gen.rootapi;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelFeatureFilter;
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
					+ "(%s.class)).with%s(%s).createNow()");

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

	public List<EOperation> getAllRootAPINewOperations(EClass rootAPICls, EClass initialisationSuperTypeEClass,
			List<EClass> initEClss, List<EClass> eObjEClss, FluentAPITargetMetamodelFeatureFilter filter) {
		var ops = new ArrayList<EOperation>();
		ops.add(getRootAPITopLevelNewOperation(rootAPICls, initialisationSuperTypeEClass));
		ops.add(getRootAPITopLevelNewOperationWithClassParameter(rootAPICls, initialisationSuperTypeEClass));

		for (int i = 0; i < eObjEClss.size(); i++) {
			var eObjEClass = eObjEClss.get(i);
			var initEClass = initEClss.get(i);

			var modifiableFeatures = filter.getModifiableFeatures(eObjEClass);
			var modifiableFeatureCount = modifiableFeatures.size();

			if (modifiableFeatureCount == 0) {
				// Add direct creation methods, if there are no modifiable features
				ops.add(getRootAPINewOperationForEClassWithoutModifiableFeats(eObjEClass, initEClass));
			} else {
				// Add methods that lead to XInitialisation instances, if there are multiple
				// modifiable features
				ops.add(getRootAPINewOperationForEClassWithModifiableFeats(rootAPICls, eObjEClass, initEClass));
			}

			// Add overloading method for types with only one modifiable feature to reduce
			// verbosity
			if (modifiableFeatureCount == 1) {
				ops.addAll(getRootAPINewOperationForEClassWithOnlyOneModifiableFeat(eObjEClass,
						modifiableFeatures.get(0), initEClass));
			}

		}

		return ops;
	}

	public EOperation getRootAPITopLevelNewOperation(EClass rootAPICls, EClass initialisationSuperTypeEClass) {
		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodEClassParameterName(),
				EcorePackage.Literals.ECLASS);
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPINewXMethodName(), initialisationSuperTypeEClass);
		FluentAPIGenerationUtil.addBody(op, String.format(newXMethodBodyTemplate,
				FluentAPIGenerationUtil.getFullyQualifiedEClassName(initialisationSuperTypeEClass)));
		FluentAPIGenerationUtil.addEParameters(op, param);
		return op;
	}

	public EOperation getRootAPITopLevelNewOperationWithClassParameter(EClass rootAPICls,
			EClass initialisationSuperTypeEClass) {
		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodClassParameterName(),
				EcorePackage.Literals.EJAVA_CLASS);
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPINewXMethodName(), initialisationSuperTypeEClass);
		FluentAPIGenerationUtil.addBody(op, String.format(newXWithClassParamMethodBodyTemplate,
				FluentAPIGenerationUtil.getFullyQualifiedEClassName(initialisationSuperTypeEClass)));
		FluentAPIGenerationUtil.addEParameters(op, param);
		return op;
	}

	public EOperation getRootAPINewOperationForEClassWithModifiableFeats(EClass rootAPICls, EClass eObjEClass,
			EClass initECls) {
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodNameForType(eObjEClass), initECls);
		FluentAPIGenerationUtil.addBody(op,
				String.format(newXWithModifiableFeatsMethodBodyTemplate,
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
						eObjEClass.getInstanceClass().getName()));
		return op;
	}

	public List<EOperation> getRootAPINewOperationForEClassWithOnlyOneModifiableFeat(EClass eObjEClass,
			EStructuralFeature modifiableFeature, EClass initECls) {

		var ops = new ArrayList<EOperation>();

		if (modifiableFeature.isMany()) {
			// Many-valued features should also have a method that accepts one value (for
			// convenience)
			ops.addAll(getRootAPINewOperationForEClassWithOnlyOneModifiableManyValuedFeat_SingleValue(eObjEClass,
					modifiableFeature, initECls));
			ops.addAll(getRootAPINewOperationForEClassWithOnlyOneModifiableManyValuedFeat_MultipleValues(eObjEClass,
					modifiableFeature, initECls));
		} else {
			ops.addAll(getRootAPINewOperationForEClassWithOnlyOneModifiableSingleValuedFeat(eObjEClass,
					modifiableFeature, initECls));
		}

		return ops;
	}

	public List<EOperation> getRootAPINewOperationForEClassWithOnlyOneModifiableSingleValuedFeat(EClass eObjEClass,
			EStructuralFeature modifiableFeature, EClass initECls) {
		var ops = new ArrayList<EOperation>();
		var originalOpFeatureValParam = getSingleValuedFeatValParam(modifiableFeature);

		var originalOp = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodNameForType(eObjEClass), eObjEClass);
		FluentAPIGenerationUtil.addBody(originalOp, String.format(
				newXWithOnlyOneModifiableSingleValuedFeatsMethodBodyTemplate, eObjEClass.getInstanceClass().getName(),
				FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls), eObjEClass.getInstanceClass().getName(),
				StringUtils.capitalize(modifiableFeature.getName()), originalOpFeatureValParam.getName()));
		FluentAPIGenerationUtil.addEParameters(originalOp, originalOpFeatureValParam);
		ops.add(originalOp);

		if (originalOpFeatureValParam.getEType().equals(EcorePackage.Literals.EBIG_INTEGER)) {
			var longOpNewFeatValParam = getSingleValuedFeatValParam(modifiableFeature);
			longOpNewFeatValParam.setEType(EcorePackage.Literals.ELONG);
			var longOp = FluentAPIGenerationUtil.generateEOperation(
					FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodNameForType(eObjEClass), eObjEClass);
			FluentAPIGenerationUtil.addBody(longOp,
					String.format(newXWithOnlyOneModifiableSingleValuedFeatsMethodBodyTemplate,
							eObjEClass.getInstanceClass().getName(),
							FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
							eObjEClass.getInstanceClass().getName(),
							StringUtils.capitalize(modifiableFeature.getName()),
							// TODO Remove valueOf, since initialisations already handle it
							String.format("java.math.BigInteger.valueOf(%s)", longOpNewFeatValParam.getName())));
			FluentAPIGenerationUtil.addEParameters(longOp, longOpNewFeatValParam);
			ops.add(longOp);

			var intOpNewFeatValParam = getSingleValuedFeatValParam(modifiableFeature);
			intOpNewFeatValParam.setEType(EcorePackage.Literals.EINT);
			var intOp = FluentAPIGenerationUtil.generateEOperation(
					FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodNameForType(eObjEClass), eObjEClass);
			FluentAPIGenerationUtil.addBody(intOp,
					String.format(newXWithOnlyOneModifiableSingleValuedFeatsMethodBodyTemplate,
							eObjEClass.getInstanceClass().getName(),
							FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
							eObjEClass.getInstanceClass().getName(),
							StringUtils.capitalize(modifiableFeature.getName()),
							// TODO Remove valueOf, since initialisations already handle it
							String.format("java.math.BigInteger.valueOf(%s)", intOpNewFeatValParam.getName())));
			FluentAPIGenerationUtil.addEParameters(intOp, intOpNewFeatValParam);
			ops.add(intOp);
		}
		return ops;
	}

	public List<EOperation> getRootAPINewOperationForEClassWithOnlyOneModifiableManyValuedFeat_SingleValue(
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

	public List<EOperation> getRootAPINewOperationForEClassWithOnlyOneModifiableManyValuedFeat_MultipleValues(
			EClass eObjEClass, EStructuralFeature modifiableFeature, EClass initECls) {
		var ops = new ArrayList<EOperation>();

		var listFeatureValParam = getManyValuedFeatValParam(modifiableFeature);
		var listOp = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodNameForType(eObjEClass), eObjEClass);
		FluentAPIGenerationUtil.addBody(listOp,
				String.format(newXWithOnlyOneModifiableManyValuedFeatsMethodBodyTemplate_multipleValues,
						eObjEClass.getInstanceClass().getName(),
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
						eObjEClass.getInstanceClass().getName(), StringUtils.capitalize(modifiableFeature.getName())));
		FluentAPIGenerationUtil.addEParameters(listOp, listFeatureValParam);
		ops.add(listOp);

		var arrayFeatureValParam = getArrayFeatValParam(modifiableFeature);
		var arrayOp = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodNameForType(eObjEClass), eObjEClass);
		FluentAPIGenerationUtil.addBody(arrayOp,
				String.format(newXWithOnlyOneModifiableManyValuedFeatsMethodBodyTemplate_multipleValues,
						eObjEClass.getInstanceClass().getName(),
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
						eObjEClass.getInstanceClass().getName(), StringUtils.capitalize(modifiableFeature.getName())));
		FluentAPIGenerationUtil.addEParameters(arrayOp, arrayFeatureValParam);
		ops.add(arrayOp);

		return ops;
	}

	public EOperation getRootAPINewOperationForEClassWithoutModifiableFeats(EClass eObjEClass, EClass initECls) {
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

	private EParameter getManyValuedFeatValParam(EStructuralFeature feat) {
		var param = FluentAPIGenerationUtil.generateManyValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodFeatureValueParameterName(), feat.getEType());
		// TODO Add documentation
		return param;
	}

	private EParameter getArrayFeatValParam(EStructuralFeature feat) {
		var param = FluentAPIGenerationUtil.generateArrayValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodFeatureValueParameterName(), feat.getEType());
		// TODO Add documentation
		return param;
	}
}
