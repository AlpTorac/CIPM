package cipm.consistency.fluentapi.gen;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.methods.AbstractInitialisationMethods;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPINewOperationGenerator {
	private static final String genModelURL = "http://www.eclipse.org/emf/2002/GenModel";

	private static final String newOperationNamePrefix = "new";

	public EOperation getNewOperationFor(EClass initEClass, EClass elemToInit) {
		var op = FluentAPIGenerationUtil.generateEOperationWithBody(
				newOperationNamePrefix + elemToInit.getInstanceClass().getSimpleName(), genModelURL, initEClass,
				getNewOperationBodyFor(elemToInit));

		op.setEType(initEClass);

		return op;
	}

	public String getNewOperationBodyFor(EClass elemToInit) {
		return FluentAPIMethodsUtil.callMethodAndReturnThis(AbstractInitialisationMethods.class, "newElement",
				FluentAPIMethodsUtil.getThisArgument());
	}
}
