package cipm.consistency.fluentapi.gen;

import java.util.List;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.util.EcoreUtil;

import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPIMethodParameterOverload implements FluentAPIMethodOverload {
	private List<EClassifier> newParams;
	private List<String> adaptedParameterExpressions;
	private String newDocumentation;

	public List<EClassifier> getNewParams() {
		return newParams;
	}

	public List<String> getAdaptedParameterExpressions() {
		return adaptedParameterExpressions;
	}

	public void setNewParamTypes(List<EClassifier> newParams) {
		this.newParams = newParams;
	}

	public void setAdaptedParameterExpressions(List<String> adaptedParameterExpressions) {
		this.adaptedParameterExpressions = adaptedParameterExpressions;
	}

	public String getNewDocumentation() {
		return newDocumentation;
	}

	public void setNewDocumentation(String newDocumentation) {
		this.newDocumentation = newDocumentation;
	}

	@Override
	public List<EOperation> createOverloadingMethodsFor(EOperation opToOverload) {
		var copier = new EcoreUtil.Copier();
		var overloadingOp = (EOperation) copier.copy(opToOverload);
		copier.copyReferences();

		// Adjust parameters
		for (int i = 0; i < overloadingOp.getEParameters().size(); i++) {
			overloadingOp.getEParameters().get(i).setEType(getNewParams().get(i));
		}

		// Adjust method body
		var serialisedParameters = String.join(",", getAdaptedParameterExpressions().toArray(String[]::new));
		FluentAPIGenerationUtil.addBody(overloadingOp, FluentAPIMethodsUtil
				.joinLOC("return this." + overloadingOp.getName() + "(" + serialisedParameters + ")"));

		// Adjust documentation
		var newDoc = getNewDocumentation();
		if (newDoc != null) {
			FluentAPIGenerationUtil.addDocumentation(overloadingOp, newDoc);
		}

		return List.of(overloadingOp);
	}
}
