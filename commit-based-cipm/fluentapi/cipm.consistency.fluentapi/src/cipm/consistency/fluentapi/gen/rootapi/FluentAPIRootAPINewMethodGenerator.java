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

	private static final String eClassParamName = "eObjEClass";

	private static final String featValParamName = "featVal";

	private static final String topLevelNewMethodName = "newX";

	private static final String newMethodNameTemplate = "new%s";

	private static final String newXMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Initialisation super type class name
			// %s: EClass param name
			"return (%s)" + "this.getInitialisationForX(%s.getInstanceClass())");

	private static final String newXWithModifiableFeatsMethodBodyTemplate = FluentAPIMethodsUtil
			.joinLOC("return (%s) this.getInitialisationForX(%s.class)");

	private static final String newXWithOnlyOneModifiableSingleValuedFeatsMethodBodyTemplate = FluentAPIMethodsUtil
			.joinLOC("return (%s) ((%s) this.getInitialisationForX(%s.class)).with%s(%s).createNow()");

	private static final String newXWithOnlyOneModifiableManyValuedFeatsMethodBodyTemplate = FluentAPIMethodsUtil
			.joinLOC("return (%s) ((%s) this.getInitialisationForX(%s.class)).withAdded%s(%s).createNow()");

	private static final String newXWithoutModifiableFeatsMethodBodyTemplate = FluentAPIMethodsUtil
			.joinLOC("return (%s) ((%s) this.getInitialisationForX(%s.class)).createNow()");

	public List<EOperation> getAllRootAPINewOperations(EClass rootAPICls, EClass initialisationSuperTypeEClass,
			List<EClass> initEClss, List<EClass> eObjEClss, FluentAPITargetMetamodelFeatureFilter filter) {
		var ops = new ArrayList<EOperation>();
		ops.add(getRootAPITopLevelNewOperation(rootAPICls, initialisationSuperTypeEClass));

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
		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(eClassParamName,
				FluentAPIGenerationUtil.getEClassEClass());
		return FluentAPIGenerationUtil.generateEOperationWithBody(topLevelNewMethodName, initialisationSuperTypeEClass,
				String.format(newXMethodBodyTemplate,
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initialisationSuperTypeEClass),
						param.getName()),
				param);
	}

	public EOperation getRootAPINewOperationForEClassWithModifiableFeats(EClass rootAPICls, EClass eObjEClass,
			EClass initECls) {
		return FluentAPIGenerationUtil.generateEOperationWithBody(
				String.format(newMethodNameTemplate, eObjEClass.getInstanceClass().getSimpleName()), initECls,
				String.format(newXWithModifiableFeatsMethodBodyTemplate,
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
						eObjEClass.getInstanceClass().getName())

		);
	}

	public List<EOperation> getRootAPINewOperationForEClassWithOnlyOneModifiableFeat(EClass eObjEClass,
			EStructuralFeature modifiableFeature, EClass initECls) {
		if (modifiableFeature.isMany()) {
			return getRootAPINewOperationForEClassWithOnlyOneModifiableManyValuedFeat(eObjEClass, modifiableFeature,
					initECls);
		} else {
			return getRootAPINewOperationForEClassWithOnlyOneModifiableSingleValuedFeat(eObjEClass, modifiableFeature,
					initECls);
		}
	}

	public List<EOperation> getRootAPINewOperationForEClassWithOnlyOneModifiableSingleValuedFeat(EClass eObjEClass,
			EStructuralFeature modifiableFeature, EClass initECls) {
		var ops = new ArrayList<EOperation>();
		var originalOpFeatureValParam = getSingleValuedFeatValParam(modifiableFeature);

		var originalOp = FluentAPIGenerationUtil.generateEOperationWithBody(
				String.format(newMethodNameTemplate, eObjEClass.getInstanceClass().getSimpleName()), eObjEClass,
				String.format(newXWithOnlyOneModifiableSingleValuedFeatsMethodBodyTemplate,
						eObjEClass.getInstanceClass().getName(),
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
						eObjEClass.getInstanceClass().getName(), StringUtils.capitalize(modifiableFeature.getName()),
						originalOpFeatureValParam.getName()),
				originalOpFeatureValParam);
		ops.add(originalOp);

		if (originalOpFeatureValParam.getEType().equals(EcorePackage.Literals.EBIG_INTEGER)) {
			var longOpNewFeatValParam = getSingleValuedFeatValParam(modifiableFeature);
			longOpNewFeatValParam.setEType(EcorePackage.Literals.ELONG);
			var longOp = FluentAPIGenerationUtil.generateEOperationWithBody(
					String.format(newMethodNameTemplate, eObjEClass.getInstanceClass().getSimpleName()), eObjEClass,
					String.format(newXWithOnlyOneModifiableSingleValuedFeatsMethodBodyTemplate,
							eObjEClass.getInstanceClass().getName(),
							FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
							eObjEClass.getInstanceClass().getName(),
							StringUtils.capitalize(modifiableFeature.getName()),
							// TODO Remove valueOf, since initialisations already handle it
							String.format("java.math.BigInteger.valueOf(%s)", longOpNewFeatValParam.getName()),
							longOpNewFeatValParam.getName()),
					longOpNewFeatValParam);
			ops.add(longOp);

			var intOpNewFeatValParam = getSingleValuedFeatValParam(modifiableFeature);
			intOpNewFeatValParam.setEType(EcorePackage.Literals.EINT);
			var intOp = FluentAPIGenerationUtil.generateEOperationWithBody(
					String.format(newMethodNameTemplate, eObjEClass.getInstanceClass().getSimpleName()), eObjEClass,
					String.format(newXWithOnlyOneModifiableSingleValuedFeatsMethodBodyTemplate,
							eObjEClass.getInstanceClass().getName(),
							FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
							eObjEClass.getInstanceClass().getName(),
							StringUtils.capitalize(modifiableFeature.getName()),
							// TODO Remove valueOf, since initialisations already handle it
							String.format("java.math.BigInteger.valueOf(%s)", intOpNewFeatValParam.getName()),
							intOpNewFeatValParam.getName()),
					intOpNewFeatValParam);
			ops.add(intOp);
		}
		return ops;
	}

	public List<EOperation> getRootAPINewOperationForEClassWithOnlyOneModifiableManyValuedFeat(EClass eObjEClass,
			EStructuralFeature modifiableFeature, EClass initECls) {
		var ops = new ArrayList<EOperation>();

		var listFeatureValParam = getManyValuedFeatValParam(modifiableFeature);
		var listOp = FluentAPIGenerationUtil.generateEOperationWithBody(
				String.format(newMethodNameTemplate, eObjEClass.getInstanceClass().getSimpleName()), eObjEClass,
				String.format(newXWithOnlyOneModifiableManyValuedFeatsMethodBodyTemplate,
						eObjEClass.getInstanceClass().getName(),
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
						eObjEClass.getInstanceClass().getName(), StringUtils.capitalize(modifiableFeature.getName()),
						listFeatureValParam.getName()),
				listFeatureValParam);
		ops.add(listOp);

		var arrayFeatureValParam = getArrayFeatValParam(modifiableFeature);
		var arrayOp = FluentAPIGenerationUtil.generateEOperationWithBody(
				String.format(newMethodNameTemplate, eObjEClass.getInstanceClass().getSimpleName()), eObjEClass,
				String.format(newXWithOnlyOneModifiableManyValuedFeatsMethodBodyTemplate,
						eObjEClass.getInstanceClass().getName(),
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
						eObjEClass.getInstanceClass().getName(), StringUtils.capitalize(modifiableFeature.getName()),
						arrayFeatureValParam.getName()),
				arrayFeatureValParam);
		ops.add(arrayOp);

		return ops;
	}

	public EOperation getRootAPINewOperationForEClassWithoutModifiableFeats(EClass eObjEClass, EClass initECls) {
		return FluentAPIGenerationUtil.generateEOperationWithBody(
				String.format(newMethodNameTemplate, eObjEClass.getInstanceClass().getSimpleName()), eObjEClass,
				String.format(newXWithoutModifiableFeatsMethodBodyTemplate, eObjEClass.getInstanceClass().getName(),
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
						eObjEClass.getInstanceClass().getName()));
	}

	private EParameter getSingleValuedFeatValParam(EStructuralFeature feat) {
		return FluentAPIGenerationUtil.generateSingleValuedEParameterWithDocumentation(featValParamName,
				feat.getEType(), String.format("TODO", feat.getName()));
	}

	private EParameter getManyValuedFeatValParam(EStructuralFeature feat) {
		return FluentAPIGenerationUtil.generateManyValuedEParameterWithDocumentation(featValParamName, feat.getEType(),
				String.format("TODO", feat.getName()));
	}

	private EParameter getArrayFeatValParam(EStructuralFeature feat) {
		return FluentAPIGenerationUtil.generateArrayValuedEParameterWithDocumentation(featValParamName, feat.getEType(),
				String.format("TODO", feat.getName()));
	}
}
