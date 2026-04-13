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
import cipm.consistency.fluentapi.gen.FluentAPIRootAPIConstants;
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
		return FluentAPIGenerationUtil
				.generateEOperationWithBody(FluentAPIRootAPIConstants.getFluentAPIRootAPINewXMethodName(),
						initialisationSuperTypeEClass,
						String.format(newXMethodBodyTemplate,
								FluentAPIGenerationUtil.getFullyQualifiedEClassName(initialisationSuperTypeEClass)),
						param);
	}

	public EOperation getRootAPITopLevelNewOperationWithClassParameter(EClass rootAPICls,
			EClass initialisationSuperTypeEClass) {
		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodClassParameterName(),
				EcorePackage.Literals.EJAVA_CLASS);
		return FluentAPIGenerationUtil
				.generateEOperationWithBody(FluentAPIRootAPIConstants.getFluentAPIRootAPINewXMethodName(),
						initialisationSuperTypeEClass,
						String.format(newXWithClassParamMethodBodyTemplate,
								FluentAPIGenerationUtil.getFullyQualifiedEClassName(initialisationSuperTypeEClass)),
						param);
	}

	public EOperation getRootAPINewOperationForEClassWithModifiableFeats(EClass rootAPICls, EClass eObjEClass,
			EClass initECls) {
		return FluentAPIGenerationUtil.generateEOperationWithBody(
				FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodNameForType(eObjEClass), initECls,
				String.format(newXWithModifiableFeatsMethodBodyTemplate,
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(eObjEClass))

		);
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

		var originalOp = FluentAPIGenerationUtil.generateEOperationWithBody(
				FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodNameForType(eObjEClass), eObjEClass,
				String.format(newXWithOnlyOneModifiableSingleValuedFeatsMethodBodyTemplate,
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(eObjEClass),
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(eObjEClass), StringUtils.capitalize(modifiableFeature.getName()),
						originalOpFeatureValParam.getName()),
				originalOpFeatureValParam);
		ops.add(originalOp);

		if (originalOpFeatureValParam.getEType().equals(EcorePackage.Literals.EBIG_INTEGER)) {
			var longOpNewFeatValParam = getSingleValuedFeatValParam(modifiableFeature);
			longOpNewFeatValParam.setEType(EcorePackage.Literals.ELONG);
			var longOp = FluentAPIGenerationUtil.generateEOperationWithBody(
					FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodNameForType(eObjEClass), eObjEClass,
					String.format(newXWithOnlyOneModifiableSingleValuedFeatsMethodBodyTemplate,
							FluentAPIGenerationUtil.getFullyQualifiedEClassName(eObjEClass),
							FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
							FluentAPIGenerationUtil.getFullyQualifiedEClassName(eObjEClass),
							StringUtils.capitalize(modifiableFeature.getName()),
							// TODO Remove valueOf, since initialisations already handle it
							String.format("java.math.BigInteger.valueOf(%s)", longOpNewFeatValParam.getName())),
					longOpNewFeatValParam);
			ops.add(longOp);

			var intOpNewFeatValParam = getSingleValuedFeatValParam(modifiableFeature);
			intOpNewFeatValParam.setEType(EcorePackage.Literals.EINT);
			var intOp = FluentAPIGenerationUtil.generateEOperationWithBody(
					FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodNameForType(eObjEClass), eObjEClass,
					String.format(newXWithOnlyOneModifiableSingleValuedFeatsMethodBodyTemplate,
							FluentAPIGenerationUtil.getFullyQualifiedEClassName(eObjEClass),
							FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
							FluentAPIGenerationUtil.getFullyQualifiedEClassName(eObjEClass),
							StringUtils.capitalize(modifiableFeature.getName()),
							// TODO Remove valueOf, since initialisations already handle it
							String.format("java.math.BigInteger.valueOf(%s)", intOpNewFeatValParam.getName())),
					intOpNewFeatValParam);
			ops.add(intOp);
		}
		return ops;
	}

	public List<EOperation> getRootAPINewOperationForEClassWithOnlyOneModifiableManyValuedFeat_SingleValue(
			EClass eObjEClass, EStructuralFeature modifiableFeature, EClass initECls) {
		var ops = new ArrayList<EOperation>();

		var featureValParam = getSingleValuedFeatValParam(modifiableFeature);
		var listOp = FluentAPIGenerationUtil.generateEOperationWithBody(
				FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodNameForType(eObjEClass), eObjEClass,
				String.format(newXWithOnlyOneModifiableManyValuedFeatsMethodBodyTemplate_singleValue,
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(eObjEClass),
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(eObjEClass), StringUtils.capitalize(modifiableFeature.getName())),
				featureValParam);
		ops.add(listOp);

		return ops;
	}

	public List<EOperation> getRootAPINewOperationForEClassWithOnlyOneModifiableManyValuedFeat_MultipleValues(
			EClass eObjEClass, EStructuralFeature modifiableFeature, EClass initECls) {
		var ops = new ArrayList<EOperation>();

		var listFeatureValParam = getManyValuedFeatValParam(modifiableFeature);
		var listOp = FluentAPIGenerationUtil.generateEOperationWithBody(
				FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodNameForType(eObjEClass), eObjEClass,
				String.format(newXWithOnlyOneModifiableManyValuedFeatsMethodBodyTemplate_multipleValues,
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(eObjEClass),
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(eObjEClass), StringUtils.capitalize(modifiableFeature.getName())),
				listFeatureValParam);
		ops.add(listOp);

		var arrayFeatureValParam = getArrayFeatValParam(modifiableFeature);
		var arrayOp = FluentAPIGenerationUtil.generateEOperationWithBody(
				FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodNameForType(eObjEClass), eObjEClass,
				String.format(newXWithOnlyOneModifiableManyValuedFeatsMethodBodyTemplate_multipleValues,
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(eObjEClass),
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(eObjEClass), StringUtils.capitalize(modifiableFeature.getName())),
				arrayFeatureValParam);
		ops.add(arrayOp);

		return ops;
	}

	public EOperation getRootAPINewOperationForEClassWithoutModifiableFeats(EClass eObjEClass, EClass initECls) {
		return FluentAPIGenerationUtil.generateEOperationWithBody(
				FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodNameForType(eObjEClass), eObjEClass,
				String.format(newXWithoutModifiableFeatsMethodBodyTemplate, FluentAPIGenerationUtil.getFullyQualifiedEClassName(eObjEClass),
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(eObjEClass)));
	}

	private EParameter getSingleValuedFeatValParam(EStructuralFeature feat) {
		return FluentAPIGenerationUtil.generateSingleValuedEParameterWithDocumentation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodFeatureValueParameterName(), feat.getEType(),
				String.format("TODO", feat.getName()));
	}

	private EParameter getManyValuedFeatValParam(EStructuralFeature feat) {
		return FluentAPIGenerationUtil.generateManyValuedEParameterWithDocumentation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodFeatureValueParameterName(), feat.getEType(),
				String.format("TODO", feat.getName()));
	}

	private EParameter getArrayFeatValParam(EStructuralFeature feat) {
		return FluentAPIGenerationUtil.generateArrayValuedEParameterWithDocumentation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodFeatureValueParameterName(), feat.getEType(),
				String.format("TODO", feat.getName()));
	}
}
