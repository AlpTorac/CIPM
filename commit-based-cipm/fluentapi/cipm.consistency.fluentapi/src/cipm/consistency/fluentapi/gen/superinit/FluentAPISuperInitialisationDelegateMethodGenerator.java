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
import cipm.consistency.fluentapi.methods.FluentAPIMethodsUtil;

public class FluentAPISuperInitialisationDelegateMethodGenerator {
	private static final Pattern methodsToDelegate = Pattern.compile(String.join("|",
			new String[] { ModelConstants.FluentAPI.WithFeat.NAME.get(),
					ModelConstants.FluentAPI.WithoutFeat.NAME.get(), ModelConstants.FluentAPI.WithAddedFeat.NAME.get(),
					ModelConstants.FluentAPI.WithRemovedFeat.NAME.get(), ModelConstants.FluentAPI.CleanFeat.NAME.get(),
					ModelConstants.FluentAPI.DropInitialisation.NAME.get(),
					ModelConstants.FluentAPI.WaitForMark.NAME.get(), ModelConstants.FluentAPI.Mark.NAME.get(),
					ModelConstants.FluentAPI.Unmark.NAME.get() }));

	@SuppressWarnings("serial")
	private static final Map<Pattern, String> parameterOverrideMap = new LinkedHashMap<>() {
		{
			put(Pattern.compile(ModelConstants.GeneralParameters.USED_EOBJECT_PARAMETER_NAME.get()),
					ModelConstants.SuperInitialisation.CurrentElement.NAME.thisGetterCall());
			put(Pattern.compile(ModelConstants.GeneralParameters.MARK_VALUE_PARAMETER_NAME.get()),
					ModelConstants.SuperInitialisation.CurrentElement.NAME.thisGetterCall());
			put(Pattern.compile(ModelConstants.FluentAPI.DropInitialisation.INITIALISATION_PARAMETER_NAME.get()),
					"this");
		}
	};

	@SuppressWarnings("serial")
	private static final Map<Pattern, String> methodNameOverrideMap = new LinkedHashMap<>() {
		{
			put(Pattern.compile(ModelConstants.FluentAPI.Mark.NAME.get()),
					ModelConstants.SuperInitialisation.Mark.NAME.get());
			put(Pattern.compile(ModelConstants.FluentAPI.Unmark.NAME.get()),
					ModelConstants.SuperInitialisation.Unmark.NAME.get());
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

				// Adjust method name
				for (var e : methodNameOverrideMap.entrySet()) {
					var mnp = e.getKey();
					if (mnp.matcher(delegateOp.getName()).matches()) {
						delegateOp.setName(e.getValue());
					}
				}

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
