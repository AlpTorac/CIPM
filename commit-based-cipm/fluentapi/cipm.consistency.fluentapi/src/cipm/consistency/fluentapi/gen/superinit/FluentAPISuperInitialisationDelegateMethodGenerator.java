package cipm.consistency.fluentapi.gen.superinit;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.util.EcoreUtil;

import cipm.consistency.fluentapi.gen.FluentAPIGeneralParameterGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationContext;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.rootapi.FluentAPIRootAPIConstants;

public class FluentAPISuperInitialisationDelegateMethodGenerator {
	private static final Pattern methodsToDelegate = Pattern.compile(String.join("|",
			new String[] { FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithFeatMethodName(),
					FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithoutFeatMethodName(),
					FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithAddedFeatMethodName(),
					FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithRemovedFeatMethodName(),
					FluentAPIRootAPIConstants.getFluentAPIRootAPIXCleanFeatMethodName(),
					FluentAPIRootAPIConstants.getFluentAPIRootAPIDropInitialisationMethodName(),
					FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMethodName(),
					FluentAPIRootAPIConstants.getFluentAPIRootAPIUnmarkMethodName(),
					FluentAPIRootAPIConstants.getFluentAPIRootAPIMarkMethodName() }));

	@SuppressWarnings("serial")
	private static final Map<Pattern, String> parameterOverrideMap = new LinkedHashMap<>() {
		{
			put(Pattern.compile(FluentAPIGeneralParameterGenerator.getFluentAPIEObjectParameterName()),
					"this.getCurrentElement()");
			put(Pattern.compile(FluentAPIGeneralParameterGenerator.getFluentAPIMarkValParameterName()),
					"this.getCurrentElement()");
			put(Pattern.compile(FluentAPIRootAPIConstants.getFluentAPIRootAPIDropInitialisationParameterName()),
					"this");
		}
	};

	private static final String delegateMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Method call string (with parameters in brackets)
			"this.toAPI().%s", "return this");

	private String getSerialisedParametersFor(EOperation op) {
		return String.join(",", op.getEParameters().stream().map((p) -> p.getName()).toArray(String[]::new));
	}

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
				var serialisedOriginalMethodCall = op.getName() + "(" + getSerialisedParametersFor(op) + ")";
				var copier = new EcoreUtil.Copier();
				var delegateOp = (EOperation) copier.copy(op);
				copier.copyReferences();

				// Adjust parameters
				delegateOp.getEParameters().removeIf((p) -> parameterOverrideMap.keySet().stream()
						.anyMatch((pattern) -> pattern.matcher(p.getName()).matches()));

				// TODO Extract as utility method for clashing method signatures
				// EcoreUti.equals does not work for EParameter comparison
				var clashingMethodExists = false;
				for (var delOp : delegateOps) {
					if (!delOp.getName().equals(delegateOp.getName()))
						continue;
					if (delOp.getEParameters().size() != delegateOp.getEParameters().size())
						continue;
					clashingMethodExists = delOp.getEParameters().stream()
							.allMatch((delOpP) -> delegateOp.getEParameters().stream().anyMatch(
									(delegateOpP) -> delOpP.getName().equals(delegateOpP.getName()) && delOpP.getEType()
											.getInstanceClass().equals(delegateOpP.getEType().getInstanceClass())));
					if (clashingMethodExists)
						break;
				}
				if (clashingMethodExists)
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
