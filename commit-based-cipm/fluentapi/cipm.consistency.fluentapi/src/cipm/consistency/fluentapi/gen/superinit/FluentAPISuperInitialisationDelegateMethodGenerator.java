package cipm.consistency.fluentapi.gen.superinit;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.util.EcoreUtil;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationContext;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.FluentAPIParameterUtil;
import cipm.consistency.fluentapi.gen.ModelConstants;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPISuperInitialisationDelegateMethodGenerator {
	private static final Pattern methodsToDelegate = Pattern.compile(String.join("|",
			new String[] { ModelConstants.RootAPI.WithFeat.NAME.get(), ModelConstants.RootAPI.WithoutFeat.NAME.get(),
					ModelConstants.RootAPI.WithAddedFeat.NAME.get(), ModelConstants.RootAPI.WithRemovedFeat.NAME.get(),
					ModelConstants.RootAPI.CleanFeat.NAME.get(), ModelConstants.RootAPI.DropInitialisation.NAME.get(),
					ModelConstants.RootAPI.OnceExists.NAME.get(), ModelConstants.RootAPI.Mark.NAME.get(),
					ModelConstants.RootAPI.Unmark.NAME.get() }));

	@SuppressWarnings("serial")
	private static final Map<Pattern, String> parameterOverrideMap = new LinkedHashMap<>() {
		{
			put(Pattern.compile(ModelConstants.GeneralParameters.USED_EOBJECT_PARAMETER_NAME.get()),
					ModelConstants.SuperInitialisation.CurrentElementRef.NAME.thisGetterCall());
			put(Pattern.compile(ModelConstants.GeneralParameters.MARK_VALUE_PARAMETER_NAME.get()),
					ModelConstants.SuperInitialisation.CurrentElementRef.NAME.thisGetterCall());
			put(Pattern.compile(ModelConstants.RootAPI.DropInitialisation.INITIALISATION_PARAMETER_NAME.get()), "this");
		}
	};

	private static final String delegateMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Method call string (with parameters in brackets)
			ModelConstants.SuperInitialisation.ToAPI.NAME.thisCall() + ".%s", "return this");

	private String replaceParameters(String serialisedParameters) {
		var result = serialisedParameters;
		for (var e : parameterOverrideMap.entrySet()) {
			var pattern = e.getKey();
			var replacement = e.getValue();
			result = result.replaceAll(pattern.pattern(), replacement);
		}
		return result;
	}

	public List<EOperation> generateAllDelegateMethods(FluentAPIGenerationContext context) {
		var delegateOps = new ArrayList<EOperation>();
		var fluentAPIECls = context.getFluentAPIECls();
		for (var op : fluentAPIECls.getEOperations()) {
			if (methodsToDelegate.matcher(op.getName()).matches()) {
				var serialisedOriginalMethodCall = op.getName() + "("
						+ FluentAPIParameterUtil.getSerialisedParametersFor(op) + ")";
				var copier = new EcoreUtil.Copier();
				var delegateOp = (EOperation) copier.copy(op);
				copier.copyReferences();

				// Adjust parameters
				delegateOp.getEParameters().removeIf((p) -> parameterOverrideMap.keySet().stream()
						.anyMatch((pattern) -> pattern.matcher(p.getName()).matches()));

				if (FluentAPIParameterUtil.hasClashingMethods(delegateOps, delegateOp))
					continue;

				// Adjust return type
				delegateOp.setEType(context.getInitSuperECls());

				// Adjust body
				FluentAPIGenerationUtil.addBody(delegateOp,
						String.format(delegateMethodBodyTemplate, replaceParameters(serialisedOriginalMethodCall)));

				delegateOps.add(delegateOp);
			}
		}
		return delegateOps;
	}
}
