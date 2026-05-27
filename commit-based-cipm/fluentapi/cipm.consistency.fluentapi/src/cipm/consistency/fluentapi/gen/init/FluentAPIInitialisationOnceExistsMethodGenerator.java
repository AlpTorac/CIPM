package cipm.consistency.fluentapi.gen.init;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.FluentAPIRootAPIConstants;
import cipm.consistency.fluentapi.gen.FluentAPISuperInitialisationConstants;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.mark.FluentAPIOnceExistsExtension;

public class FluentAPIInitialisationOnceExistsMethodGenerator {
	// TODO Add documentation (for generated methods)

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

	public List<EOperation> generateAllOnceExistsMethods(EClass initECls) {
		var ops = new ArrayList<EOperation>();
		ops.add(generateOnceExistsMethod(initECls));
		ops.add(generateOnceExistsListMethod(initECls));
		ops.add(generateOnceExistsArrayMethod(initECls));
		return ops;
	}

	private EOperation generateOnceExistsMethod(EClass initECls) {
		var keyParam = getMarkKeyParam();
		var consumerParam = getRunnableParam();
		return FluentAPIGenerationUtil.generateEOperationWithBody(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMethodName(), initECls,
				onceExistsMethodSingleMarkedKeyBody, keyParam, consumerParam);
	}

	private EOperation generateOnceExistsListMethod(EClass initECls) {
		var keyParam = getMarkKeyListParam();
		var consumerParam = getRunnableParam();
		return FluentAPIGenerationUtil.generateEOperationWithBody(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMethodName(), initECls,
				onceExistsMethodMultipleMarkedKeyBody, keyParam, consumerParam);
	}

	private EOperation generateOnceExistsArrayMethod(EClass initECls) {
		var keyParam = getMarkKeyArrayParam();
		var consumerParam = getRunnableParam();
		return FluentAPIGenerationUtil.generateEOperationWithBody(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMethodName(), initECls,
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
}
