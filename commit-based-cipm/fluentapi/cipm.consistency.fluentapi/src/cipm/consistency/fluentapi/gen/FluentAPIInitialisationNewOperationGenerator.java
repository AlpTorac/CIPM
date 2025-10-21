package cipm.consistency.fluentapi.gen;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.methods.AbstractInitialisationMethods;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPIInitialisationNewOperationGenerator {
	private static final String genModelURL = "http://www.eclipse.org/emf/2002/GenModel";

	private static final String newOperationNamePrefix = "new";

	private static final String newOperationMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			//
			AbstractInitialisationMethods.class.getName() + ".newElement(this)",
			//
			"return this");

	public EOperation getNewOperationFor(EClass initEClass, EClass elemToInit) {
		var op = FluentAPIGenerationUtil.generateEOperationWithBody(
				newOperationNamePrefix + elemToInit.getInstanceClass().getSimpleName(), genModelURL, initEClass,
				newOperationMethodBodyTemplate);

		op.setEType(initEClass);

		return op;
	}
}
