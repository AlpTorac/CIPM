package cipm.consistency.fluentapi.gen.rootapi;

import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationContext;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIInitialisationStorage;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPIRootAPIGetOngoingInitsMethodGenerator {
	private static final String getOngoingInitsMethodBody = FluentAPIMethodsUtil
			// %s: Fully qualified super initialisation class name
			.joinLOC("return " + FluentAPIInitialisationStorage.class.getName() + "."
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIGetOngoingInitsMethodName()
					+ "().stream().map((i) -> (%s) i).collect(" + java.util.stream.Collectors.class.getName()
					+ ".toList())");

	public EOperation generateGetOngoingInitsMethod(FluentAPIGenerationContext context) {
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIGetOngoingInitsMethodName(),
				FluentAPIGenerationUtil.generateEGenericTypeWithTypeArgument(context, java.util.List.class,
						FluentAPIGenerationUtil.generateEGenericTypeWithClassifier(context.getInitSuperECls())));
		FluentAPIGenerationUtil.addBody(op, String.format(getOngoingInitsMethodBody,
				FluentAPIGenerationUtil.getFullyQualifiedEClassName(context.getInitSuperECls())));
		return op;
	}
}
