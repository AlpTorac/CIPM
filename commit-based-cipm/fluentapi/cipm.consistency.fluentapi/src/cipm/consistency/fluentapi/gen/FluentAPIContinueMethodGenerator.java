package cipm.consistency.fluentapi.gen;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.FluentEObjectAPIMethods;

public class FluentAPIContinueMethodGenerator {
	private static final String genModelURL = "http://www.eclipse.org/emf/2002/GenModel";

	private static final String continueMethodNameTemplate = "continue%s";
	private static final String continueMethodBodyTemplate = FluentAPIMethodsUtil
			.joinLOC("return (%s) " + FluentEObjectAPIMethods.class.getName() + ".continueElement(this, %s.class)");

	private static final String indexFromStartParamName = "idxFromStart";
	private static final String continueFromStartMethodNameTemplate = "continue%sFromStart";
	private static final String continueFromStartMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			"return (%s) " + FluentEObjectAPIMethods.class.getName() + ".continueElementFromStart(this, %s.class, %s)");

	private static final String indexFromEndParamName = "idxFromEnd";
	private static final String continueFromEndMethodNameTemplate = "continue%sFromEnd";
	private static final String continueFromEndMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			"return (%s) " + FluentEObjectAPIMethods.class.getName() + ".continueElementFromEnd(this, %s.class, %s)");

	public List<EOperation> generateAllContinueMethods(List<EClass> initEClss, List<EClass> eObjEClss,
			FluentAPITargetMetamodelFeatureFilter filter) {

		var ops = new ArrayList<EOperation>();

		for (int i = 0; i < eObjEClss.size(); i++) {
			var eObjEClass = eObjEClss.get(i);
			var initEClass = initEClss.get(i);
			if (FluentAPIGenerationUtil.hasModifiableFeatures(eObjEClass, filter)) {
				ops.add(generateContinueMethod(eObjEClass, initEClass));
				ops.add(generateContinueFromStartMethod(eObjEClass, initEClass));
				ops.add(generateContinueFromEndMethod(eObjEClass, initEClass));
			}
		}

		return ops;
	}

	public EOperation generateContinueMethod(EClass elemToInit, EClass initECls) {
		return FluentAPIGenerationUtil.generateEOperationWithBody(
				String.format(continueMethodNameTemplate, StringUtils.capitalize(elemToInit.getName())), genModelURL,
				initECls,
				String.format(continueMethodBodyTemplate, FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
						elemToInit.getInstanceClass().getName()));
	}

	public EOperation generateContinueFromStartMethod(EClass elemToInit, EClass initECls) {
		var param = this.getIndexParam(indexFromStartParamName);
		return FluentAPIGenerationUtil.generateEOperationWithBody(
				String.format(continueFromStartMethodNameTemplate, StringUtils.capitalize(elemToInit.getName())),
				genModelURL, initECls,
				String.format(continueFromStartMethodBodyTemplate,
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
						elemToInit.getInstanceClass().getName(), param.getName()),
				param);
	}

	public EOperation generateContinueFromEndMethod(EClass elemToInit, EClass initECls) {
		var param = this.getIndexParam(indexFromEndParamName);
		return FluentAPIGenerationUtil.generateEOperationWithBody(
				String.format(continueFromEndMethodNameTemplate, StringUtils.capitalize(elemToInit.getName())),
				genModelURL, initECls,
				String.format(continueFromEndMethodBodyTemplate,
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
						elemToInit.getInstanceClass().getName(), param.getName()),
				param);
	}

	public EParameter getIndexParam(String name) {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(name, EcorePackage.Literals.EINT);
	}
}
