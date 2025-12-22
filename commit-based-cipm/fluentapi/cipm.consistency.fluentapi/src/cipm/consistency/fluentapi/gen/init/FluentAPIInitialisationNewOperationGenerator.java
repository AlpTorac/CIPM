package cipm.consistency.fluentapi.gen.init;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.FluentAPIConstants;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPIInitialisationNewOperationGenerator {
	private static final String newOperationName = "newElement";

	private static final String newOperationMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Fully qualified name of the concrete EPackage type
			"var pac = %s.eINSTANCE",
			// %s: Fully qualified name of EClass class
			// %s: Name of the EClass of the element to initialise
			"this.setCurrentElement(pac.getEFactoryInstance().create((%s) pac.getEClassifier(\"%s\")))",
			//
			"return this");

	public static String getNewOperationName() {
		return newOperationName;
	}

	public EOperation getNewOperationFor(EClass initEClass, EClass elemToInit) {
		var op = FluentAPIGenerationUtil.generateEOperationWithBody(newOperationName,
				FluentAPIConstants.getGenModelURL(), initEClass, String.format(newOperationMethodBodyTemplate,
						elemToInit.getEPackage().getClass().getName(), EClass.class.getName(), elemToInit.getName()));

		op.setEType(initEClass);

		return op;
	}
}
