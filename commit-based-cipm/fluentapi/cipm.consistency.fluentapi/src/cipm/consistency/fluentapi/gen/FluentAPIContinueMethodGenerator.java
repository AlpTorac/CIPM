package cipm.consistency.fluentapi.gen;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.FluentEObjectAPIMethods;

public class FluentAPIContinueMethodGenerator {
	private static final String genModelURL = "http://www.eclipse.org/emf/2002/GenModel";

	private static final String continueMethodNameTemplate = "continue%s";

	private static final String continueMethodBodyTemplate = FluentAPIMethodsUtil
			.joinLOC("return (%s) " + FluentEObjectAPIMethods.class.getName() + ".getInitialisationForX(this, %s.class)");

	public List<EOperation> generateAllContinueMethods(List<EClass> initEClss, List<EClass> eObjEClss,
			FluentAPITargetMetamodelFeatureFilter filter) {

		var ops = new ArrayList<EOperation>();

		for (int i = 0; i < eObjEClss.size(); i++) {
			var eObjEClass = eObjEClss.get(i);
			var initEClass = initEClss.get(i);
			if (FluentAPIGenerationUtil.hasModifiableFeatures(eObjEClass, filter)) {
				ops.add(generateContinueMethod(eObjEClass, initEClass));
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
}
