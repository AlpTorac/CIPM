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
import cipm.consistency.fluentapi.gen.methods.mark.FluentAPIOnceExistsExtension;
import cipm.consistency.fluentapi.gen.rootapi.FluentAPIRootAPIConstants;

public class FluentAPISuperInitialisationOnceExistsMethodGenerator implements IFluentAPIMethodGenerator {

	private static final String onceExistsMethodSingleMarkedKeyBody = FluentAPIMethodsUtil
			.joinLOC(FluentAPIOnceExistsExtension.class.getName() + ".addOnceExists(this.get"
					+ FluentAPISuperInitialisationConstants
							.getCapitalisedFluentAPISuperInitialisationRootAPIReferenceName()
					+ "(), "
					+ FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationMarkKeyParameterName()
					+ ", (java.lang.Runnable) " + FluentAPISuperInitialisationConstants
							.getFluentAPISuperInitialisationOnceExistsRunnableParameterName()
					+ ")", "return this");

	private static final String onceExistsMethodMultipleMarkedKeyBody = FluentAPIMethodsUtil
			.joinLOC(
					FluentAPIOnceExistsExtension.class.getName() + ".addOnceExists(this.get"
							+ FluentAPISuperInitialisationConstants
									.getCapitalisedFluentAPISuperInitialisationRootAPIReferenceName()
							+ "(), "
							+ FluentAPISuperInitialisationConstants
									.getFluentAPISuperInitialisationOnceExistsMarkKeyListParameterName()
							+ ", (java.lang.Runnable) "
							+ FluentAPISuperInitialisationConstants
									.getFluentAPISuperInitialisationOnceExistsRunnableParameterName()
							+ ")",
					"return this");

	public List<EOperation> generateAllOnceExistsMethods(EClass superInitECls) {
		var ops = new ArrayList<EOperation>();
		ops.add(generateOnceExistsMethod(superInitECls));
		ops.add(generateOnceExistsListMethod(superInitECls));
		ops.add(generateOnceExistsArrayMethod(superInitECls));
		return ops;
	}

	private EOperation generateOnceExistsMethod(EClass superInitECls) {
		var keyParam = getMarkKeyParam();
		var consumerParam = getRunnableParam();
		return FluentAPIGenerationUtil.generateEOperationWithBody(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMethodName(), superInitECls,
				onceExistsMethodSingleMarkedKeyBody, keyParam, consumerParam);
	}

	private EOperation generateOnceExistsListMethod(EClass superInitECls) {
		var keyParam = getMarkKeyListParam();
		var consumerParam = getRunnableParam();
		return FluentAPIGenerationUtil.generateEOperationWithBody(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMethodName(), superInitECls,
				onceExistsMethodMultipleMarkedKeyBody, keyParam, consumerParam);
	}

	private EOperation generateOnceExistsArrayMethod(EClass superInitECls) {
		var keyParam = getMarkKeyArrayParam();
		var consumerParam = getRunnableParam();
		return FluentAPIGenerationUtil.generateEOperationWithBody(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMethodName(), superInitECls,
				onceExistsMethodMultipleMarkedKeyBody, keyParam, consumerParam);
	}

	private EParameter getMarkKeyParam() {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIMarkKeyParameterName(),
				EcorePackage.Literals.EJAVA_OBJECT);
	}

	private EParameter getMarkKeyListParam() {
		return FluentAPIGenerationUtil.generateManyValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMarkKeyListParameterName(),
				EcorePackage.Literals.EJAVA_OBJECT);
	}

	private EParameter getMarkKeyArrayParam() {
		return FluentAPIGenerationUtil.generateArrayValuedEParameterWithDocumentation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMarkKeyListParameterName(),
				EcorePackage.Literals.EJAVA_OBJECT, "TODO");
	}

	private EParameter getRunnableParam() {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsRunnableParameterName(), Runnable.class);
	}

	@Override
	public Map<String, String> getMethodNamesToDescriptions() {
		return Map.of(FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMethodName(),
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMethodSummary());
	}
}
