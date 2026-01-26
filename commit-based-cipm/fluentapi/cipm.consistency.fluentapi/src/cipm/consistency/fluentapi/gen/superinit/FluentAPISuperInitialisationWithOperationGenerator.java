package cipm.consistency.fluentapi.gen.superinit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.IFluentAPIMethodGenerator;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.FluentEObjectAPIMethods;
import cipm.consistency.fluentapi.gen.rootapi.FluentAPIRootAPIConstants;

public class FluentAPISuperInitialisationWithOperationGenerator implements IFluentAPIMethodGenerator {
	private static final String xWithFeatMethodBody = FluentAPIMethodsUtil.joinLOC(
			FluentEObjectAPIMethods.class.getName() + ".xWithFeat(this."
					+ FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationToAPIMethodName()
					+ "(), this.get"
					+ FluentAPISuperInitialisationConstants
							.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
					+ "(), " + FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodFeatureParameterName() + ", "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodFeatureValueParameterName() + ")",
			"return this");

	private static final String xWithoutFeatMethodBody = FluentAPIMethodsUtil.joinLOC(
			FluentEObjectAPIMethods.class.getName() + ".xWithoutFeat(this."
					+ FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationToAPIMethodName()
					+ "(), this.get"
					+ FluentAPISuperInitialisationConstants
							.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
					+ "(), " + FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodFeatureParameterName() + ")",
			"return this");

	private static final String xWithAddedFeatMethodBody = FluentAPIMethodsUtil.joinLOC(
			FluentEObjectAPIMethods.class.getName() + ".xWithAddedFeat(this."
					+ FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationToAPIMethodName()
					+ "(), this.get"
					+ FluentAPISuperInitialisationConstants
							.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
					+ "(), " + FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodFeatureParameterName() + ", "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodFeatureValueParameterName() + ")",
			"return this");

	private static final String xWithRemovedFeatMethodBody = FluentAPIMethodsUtil.joinLOC(
			FluentEObjectAPIMethods.class.getName() + ".xWithRemovedFeat(this."
					+ FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationToAPIMethodName()
					+ "(), this.get"
					+ FluentAPISuperInitialisationConstants
							.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
					+ "(), " + FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodFeatureParameterName() + ", "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodFeatureValueParameterName() + ")",
			"return this");

	private static final String xWithExactFeatMethodBody = FluentAPIMethodsUtil.joinLOC(
			FluentEObjectAPIMethods.class.getName() + ".xWithExactFeat(this."
					+ FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationToAPIMethodName()
					+ "(), this.get"
					+ FluentAPISuperInitialisationConstants
							.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
					+ "(), " + FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodFeatureParameterName() + ", "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodFeatureValueParameterName() + ")",
			"return this");

	private static final String xWithFeatOfContainerMethodBody = FluentAPIMethodsUtil.joinLOC(
			FluentEObjectAPIMethods.class.getName() + ".xWithFeatOfContainer(this."
					+ FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationToAPIMethodName()
					+ "(), this.get"
					+ FluentAPISuperInitialisationConstants
							.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
					+ "(), " + FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodFeatureParameterName() + ")",
			"return this");

	public List<EOperation> getAllAPITopLevelWithOperations(EClass superInitCls) {
		var ops = new ArrayList<EOperation>();

		ops.add(getWithFeatOp(superInitCls));
		ops.add(getWithoutFeatOp(superInitCls));
		ops.add(getWithAddedFeatOp(superInitCls));
		ops.add(getWithRemovedFeatOp(superInitCls));
		ops.add(getWithExactFeatOp(superInitCls));
		ops.add(getWithAddedFeatArrayOp(superInitCls));
		ops.add(getWithRemovedFeatArrayOp(superInitCls));
		ops.add(getWithExactFeatArrayOp(superInitCls));
		ops.add(getWithFeatOfContainerOp(superInitCls));

		return ops;
	}

	private EOperation getWithFeatOp(EClass superInitCls) {
		var featParam = getFeatParam();
		var featValParam = getFeatValParam();

		var op = FluentAPIGenerationUtil
				.generateEOperation(FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithFeatMethodName(), superInitCls);
		FluentAPIGenerationUtil.addBody(op, xWithFeatMethodBody);
		FluentAPIGenerationUtil.addEParameters(op, featParam, featValParam);
		return op;
	}

	private EOperation getWithoutFeatOp(EClass superInitCls) {
		var featParam = getFeatParam();

		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithoutFeatMethodName(), superInitCls);
		FluentAPIGenerationUtil.addBody(op, xWithoutFeatMethodBody);
		FluentAPIGenerationUtil.addEParameters(op, featParam);
		return op;
	}

	private EOperation getWithAddedFeatOp(EClass superInitCls) {
		var featParam = getFeatParam();
		var featValParam = getFeatValParam();

		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithAddedFeatMethodName(), superInitCls);
		FluentAPIGenerationUtil.addBody(op, xWithAddedFeatMethodBody);
		FluentAPIGenerationUtil.addEParameters(op, featParam, featValParam);
		return op;
	}

	private EOperation getWithRemovedFeatOp(EClass superInitCls) {
		var featParam = getFeatParam();
		var featValParam = getFeatValParam();

		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithRemovedFeatMethodName(), superInitCls);
		FluentAPIGenerationUtil.addBody(op, xWithRemovedFeatMethodBody);
		FluentAPIGenerationUtil.addEParameters(op, featParam, featValParam);
		return op;
	}

	private EOperation getWithExactFeatOp(EClass superInitCls) {
		var featParam = getFeatParam();
		var featValParam = getFeatValParam();

		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithExactFeatMethodName(), superInitCls);
		FluentAPIGenerationUtil.addBody(op, xWithExactFeatMethodBody);
		FluentAPIGenerationUtil.addEParameters(op, featParam, featValParam);
		return op;
	}

	private EOperation getWithAddedFeatArrayOp(EClass superInitCls) {
		var featParam = getFeatParam();
		var featValParam = getFeatValArrayParam();

		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithAddedFeatMethodName(), superInitCls);
		FluentAPIGenerationUtil.addBody(op, xWithAddedFeatMethodBody);
		FluentAPIGenerationUtil.addEParameters(op, featParam, featValParam);
		return op;
	}

	private EOperation getWithRemovedFeatArrayOp(EClass superInitCls) {
		var featParam = getFeatParam();
		var featValParam = getFeatValArrayParam();

		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithRemovedFeatMethodName(), superInitCls);
		FluentAPIGenerationUtil.addBody(op, xWithRemovedFeatMethodBody);
		FluentAPIGenerationUtil.addEParameters(op, featParam, featValParam);
		return op;
	}

	private EOperation getWithExactFeatArrayOp(EClass superInitCls) {
		var featParam = getFeatParam();
		var featValParam = getFeatValArrayParam();

		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithExactFeatMethodName(), superInitCls);
		FluentAPIGenerationUtil.addBody(op, xWithExactFeatMethodBody);
		FluentAPIGenerationUtil.addEParameters(op, featParam, featValParam);
		return op;
	}

	private EOperation getWithFeatOfContainerOp(EClass superInitCls) {
		var featParam = getFeatParam();

		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithFeatOfContainerMethodName(), superInitCls);
		FluentAPIGenerationUtil.addBody(op, xWithFeatOfContainerMethodBody);
		FluentAPIGenerationUtil.addEParameters(op, featParam);
		return op;
	}

	private EParameter getFeatParam() {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodFeatureParameterName(),
				EcorePackage.Literals.ESTRUCTURAL_FEATURE);
	}

	private EParameter getFeatValParam() {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodFeatureValueParameterName(),
				EcorePackage.Literals.EJAVA_OBJECT);
	}

	private EParameter getFeatValArrayParam() {
		return FluentAPIGenerationUtil.generateArrayValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodFeatureValueParameterName(),
				EcorePackage.Literals.EJAVA_OBJECT);
	}

	@Override
	public Map<String, String> getMethodNamesToDescriptions() {
		return Map.of(FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithFeatMethodName(),
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithFeatMethodSummary(),

				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithoutFeatMethodName(),
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithoutFeatMethodSummary(),

				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithFeatOfContainerMethodName(),
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithFeatOfContainerMethodSummary(),

				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithAddedFeatMethodName(),
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithAddedFeatMethodSummary(),

				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithRemovedFeatMethodName(),
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithRemovedFeatMethodSummary(),

				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithExactFeatMethodName(),
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithExactFeatMethodSummary());
	}
}
