package cipm.consistency.fluentapi.gen.init;

import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.FluentAPIDocumentationUtil;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.IFluentAPIMethodGenerator;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.superinit.FluentAPISuperInitialisationConstants;

public class FluentAPIInitialisationNewElementOperationGenerator implements IFluentAPIMethodGenerator {
	// %s: Name of elemToInit
	private static final String newElementMethodDocumentation = FluentAPIDocumentationUtil.appendSummaryToStart(
			FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationNewElementMethodSummary())
			+ "Creates a minimal %s instance, without modifying any of its features, and sets it as the current element (i.e. return value of this.get"
			+ FluentAPISuperInitialisationConstants
					.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
			+ "())." + FluentAPIDocumentationUtil.appendDoNotUseFromOutsideDocNoteAtEnd();

	private static final String newElementMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
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

	public EOperation getNewElementOperationFor(EClass initEClass, EClass elemToInit) {
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIInitialisationConstants.getFluentAPIInitialisationNewElementOperationName(), initEClass);
		FluentAPIGenerationUtil.addBody(op, String.format(newElementMethodBodyTemplate,
				elemToInit.getEPackage().getClass().getName(), EClass.class.getName(), elemToInit.getName()));
		FluentAPIGenerationUtil.addDocumentation(op,
				String.format(newElementMethodDocumentation, elemToInit.getName()));
		return op;
	}

	@Override
	public Map<String, String> getMethodNamesToDescriptions() {
		return Map.of(FluentAPIInitialisationConstants.getFluentAPIInitialisationNewElementOperationName(),
				FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationNewElementMethodSummary());
	}
}
