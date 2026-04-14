package cipm.consistency.fluentapi.gen.init;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.FluentAPIInitialisationConstants;
import cipm.consistency.fluentapi.gen.FluentAPISuperInitialisationConstants;
import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelPackageProvider;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPIInitialisationNewElementOperationGenerator {
	// %s: Name of elemToInit
	private static final String newElementOperationDocumentation = "Creates a minimal %s instance, without modifying any of its features, and sets it as the current element (i.e. return value of this.get"
			+ FluentAPISuperInitialisationConstants
					.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
			+ "()).";

	private static final String newElementOperationMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Fully qualified name of the concrete EPackage type
			"var pac = %s.eINSTANCE",
			// %s: Fully qualified name of EClass class
			// %s: Name of the EClass of the element to initialise
			"this.set"
					+ FluentAPISuperInitialisationConstants
							.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
					+ "(pac.getEFactoryInstance().create((%s) pac.getEClassifier(\"%s\")))",
			//
			"return this");

	public EOperation getNewElementOperationFor(EClass initEClass, EClass elemToInit,
			FluentAPITargetMetamodelPackageProvider provider) {
		var op = FluentAPIGenerationUtil.generateEOperationWithBodyAndDocumentation(
				FluentAPIInitialisationConstants.getFluentAPIInitialisationNewElementOperationName(), initEClass,
				String.format(newElementOperationMethodBodyTemplate,
						provider.getFullyQualifiedPackageNameFor(elemToInit), EClass.class.getName(),
						elemToInit.getName()),
				String.format(newElementOperationDocumentation, elemToInit.getName()));

		op.setEType(initEClass);

		return op;
	}
}
