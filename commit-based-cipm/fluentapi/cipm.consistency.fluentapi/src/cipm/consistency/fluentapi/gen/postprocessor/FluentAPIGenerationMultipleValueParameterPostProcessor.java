package cipm.consistency.fluentapi.gen.postprocessor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.util.EcoreUtil;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationContext;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;

public abstract class FluentAPIGenerationMultipleValueParameterPostProcessor
		implements FluentAPIGenerationPostProcessor {

	private EParameter getArrayVersion(FluentAPIGenerationContext context, EParameter oldParam) {
		var param = FluentAPIGenerationUtil.generateArrayValuedEParameter(context, oldParam.getName(),
				oldParam.getEType());
		FluentAPIGenerationUtil.useDocumentationOf(param, oldParam);
		return param;
	}

	private EParameter getColVersion(FluentAPIGenerationContext context, EParameter oldParam) {
		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(oldParam.getName(),
				FluentAPIGenerationUtil.generateCollectionTypeParameter(context, oldParam.getEType()));
		FluentAPIGenerationUtil.useDocumentationOf(param, oldParam);
		return param;
	}

	protected abstract EOperation overloadMethodBody(EOperation overloadingOp, EParameter newParam);

	private EOperation createOverloadingMultipleValueMethodFor(FluentAPIGenerationContext context,
			EOperation opToOverload, EParameter paramToOverload, EParameter newParam) {
		var copier = new EcoreUtil.Copier();
		var overloadingOp = (EOperation) copier.copy(opToOverload);
		copier.copyReferences();

		// Adjust parameter
		var oldParamIdx = opToOverload.getEParameters().indexOf(paramToOverload);
		var oldParam = overloadingOp.getEParameters().get(oldParamIdx);

		overloadingOp.getEParameters().add(oldParamIdx, newParam);
		overloadingOp.getEParameters().remove(oldParam);

		// Adjust method body
		overloadMethodBody(overloadingOp, newParam);
		FluentAPIGenerationUtil.useDocumentationOf(overloadingOp, opToOverload);

		return overloadingOp;
	}

	private boolean hasArrayOverload(EOperation op, EParameter p) {
		var pIdx = op.getEParameters().indexOf(p);
		return op.getEContainingClass().getEOperations().stream().anyMatch((opTwo) -> op != opTwo
				&& op.getName().equals(opTwo.getName()) && op.getEType().equals(opTwo.getEType())
				&& opTwo.getEParameters().get(pIdx).getEGenericType().getEClassifier().getInstanceClass().isArray()
				&& p.getEType().getInstanceClass().equals(opTwo.getEParameters().get(pIdx).getEGenericType()
						.getEClassifier().getInstanceClass().getComponentType()));
	}

	private boolean hasColOverload(EOperation op, EParameter p) {
		var pIdx = op.getEParameters().indexOf(p);
		return op.getEContainingClass().getEOperations().stream()
				.anyMatch((opTwo) -> op != opTwo && op.getName().equals(opTwo.getName())
						&& op.getEType().equals(opTwo.getEType()) && opTwo.getEParameters().get(pIdx).getEGenericType()
								.getEClassifier().getInstanceClass().equals(Collection.class));
	}

	protected abstract boolean shouldOverloadParameter(EParameter param);

	private List<EOperation> createOverloadingMethodsFor(FluentAPIGenerationContext context, EOperation opToOverload) {
		var ops = new ArrayList<EOperation>();
		for (var p : opToOverload.getEParameters().stream().filter(this::shouldOverloadParameter)
				.collect(Collectors.toList())) {
			if (!hasArrayOverload(opToOverload, p)) {
				ops.add(createOverloadingMultipleValueMethodFor(context, opToOverload, p, getArrayVersion(context, p)));
			}
			if (!hasColOverload(opToOverload, p)) {
				ops.add(createOverloadingMultipleValueMethodFor(context, opToOverload, p, getColVersion(context, p)));
			}
		}
		return ops;
	}

	protected abstract boolean shouldOverloadMethod(EOperation op);

	@Override
	public void apply(FluentAPIGenerationContext context) {
		var allEClss = new ArrayList<EClass>();
		allEClss.add(context.getFluentAPIECls());
		allEClss.add(context.getInitSuperECls());
		allEClss.addAll(context.getAllInitEClss());

		for (var eCls : allEClss) {
			// Only consider EOperations, which have a single parameter and whose return
			// type is their containing EClass
			var opsToOverload = eCls.getEOperations().stream().filter((op) -> shouldOverloadMethod(op))
					.collect(Collectors.toList());
			for (var op : opsToOverload) {
				op.getEContainingClass().getEOperations().addAll(createOverloadingMethodsFor(context, op));
			}
		}
	}
}
