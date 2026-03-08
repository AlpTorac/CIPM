package cipm.consistency.fluentapi.gen.init;

import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.IFluentAPIMethodGenerator;
import cipm.consistency.fluentapi.gen.ModelConstants;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPIInitialisationNewElementOperationGenerator implements IFluentAPIMethodGenerator {
	private static final String newElementMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Fully qualified name of the concrete EPackage type
			"var pac = %s.eINSTANCE",
			// %s: Fully qualified name of EClass class
			// %s: Name of the EClass of the element to initialise
			ModelConstants.SuperInitialisation.CurrentElementRef.NAME
					.thisSetterCall("(pac.getEFactoryInstance().create((%s) pac.getEClassifier(\"%s\")))"),
			//
			"return this");

	public EOperation getNewElementOperationFor(EClass initEClass, EClass elemToInit) {
		var op = FluentAPIGenerationUtil.generateEOperation(
				ModelConstants.SuperInitialisation.NewElement.NAME.get(), initEClass);
		FluentAPIGenerationUtil.addBody(op, String.format(newElementMethodBodyTemplate,
				elemToInit.getEPackage().getClass().getName(), EClass.class.getName(), elemToInit.getName()));
		FluentAPIGenerationUtil.addDocumentation(op,
				ModelConstants.Initialiation.NewElement.DOC.getFor(elemToInit.getName()));
		return op;
	}

	@Override
	public Map<String, String> getMethodNamesToDescriptions() {
		return Map.of();
	}
}
