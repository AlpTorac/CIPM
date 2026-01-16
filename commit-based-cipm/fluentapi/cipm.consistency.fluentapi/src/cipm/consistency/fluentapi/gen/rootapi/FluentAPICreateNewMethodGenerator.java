package cipm.consistency.fluentapi.gen.rootapi;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

/**
 * Separated from FluentAPIRootAPINewMethodGenerator, since the "createNewX"
 * methods cannot share their name with "newX" methods, due to Java limitations.
 * 
 * TODO Add commentary
 */
public class FluentAPICreateNewMethodGenerator {
	// TODO Add documentation

	private static final String createNewXMethodNameTemplate = "createNew%s";

	private static final String createNewXMethodBodyTemplate = FluentAPIMethodsUtil
			.joinLOC("return (%s) this.getInitialisationForX(%s.class).createNow()");

	public List<EOperation> generateAllCreateNewMethods(List<EClass> eObjEClss) {
		var ops = new ArrayList<EOperation>();

		for (var eObjEClass : eObjEClss) {
			ops.add(generateCreateNewMethod(eObjEClass));
		}

		return ops;
	}

	private EOperation generateCreateNewMethod(EClass eObjEClass) {
		return FluentAPIGenerationUtil.generateEOperationWithBody(
				String.format(createNewXMethodNameTemplate, eObjEClass.getInstanceClass().getSimpleName()), eObjEClass,
				String.format(createNewXMethodBodyTemplate, eObjEClass.getInstanceClass().getName(),
						eObjEClass.getInstanceClass().getName()));
	}
}
