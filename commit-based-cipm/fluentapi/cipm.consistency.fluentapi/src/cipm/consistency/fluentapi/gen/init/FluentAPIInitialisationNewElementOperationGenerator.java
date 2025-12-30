package cipm.consistency.fluentapi.gen.init;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPIInitialisationNewElementOperationGenerator {
	private static final String newElementOperationName = "newElement";
	// %s: Name of elemToInit
	private static final String newElementOperationDocumentation = "Creates a minimal %s instance, without modifying any of its features, and sets it as the current element (i.e. return value of this.getCurrentElement()).";

	private static final String newElementOperationMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Fully qualified name of the concrete EPackage type
			"var pac = %s.eINSTANCE",
			// %s: Fully qualified name of EClass class
			// %s: Name of the EClass of the element to initialise
			"this.setCurrentElement(pac.getEFactoryInstance().create((%s) pac.getEClassifier(\"%s\")))",
			//
			"return this");

	public static String getNewElementOperationName() {
		return newElementOperationName;
	}

	public EOperation getNewElementOperationFor(EClass initEClass, EClass elemToInit) {
		var op = FluentAPIGenerationUtil.generateEOperationWithBodyAndDocumentation(newElementOperationName, initEClass,
				String.format(newElementOperationMethodBodyTemplate, elemToInit.getEPackage().getClass().getName(),
						EClass.class.getName(), elemToInit.getName()),
				String.format(newElementOperationDocumentation, elemToInit.getName()));

		op.setEType(initEClass);

		return op;
	}
}
