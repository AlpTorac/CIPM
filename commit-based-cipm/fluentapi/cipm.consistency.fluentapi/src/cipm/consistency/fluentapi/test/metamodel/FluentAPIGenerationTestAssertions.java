package cipm.consistency.fluentapi.test.metamodel;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EcorePackage;
import org.junit.jupiter.api.Assertions;

public class FluentAPIGenerationTestAssertions {
	public static void assertOriginalMethodExists(EClass eCls, List<EOperation> currentEClssOps,
			List<String> paramNames, List<EClassifier> paramTypes) {
		Assertions.assertTrue(currentEClssOps.stream().anyMatch((op) -> assertParamsEqual(op, paramNames, paramTypes)),
				"For eCls " + eCls.getName());
	}

	public static void assertMultiValueVariantsExist(EClass eCls, List<EOperation> currentEClssOps,
			List<String> paramNames, List<EClassifier> paramTypes) {
		Assertions.assertTrue(3 <= currentEClssOps.size(), "For eCls " + eCls.getName());
		Assertions.assertTrue(
				currentEClssOps.stream().anyMatch((op) -> assertArrayValuedParamsEqual(op, paramNames, paramTypes)));
		Assertions.assertTrue(currentEClssOps.stream()
				.anyMatch((op) -> assertCollectionValuedParamsEqual(op, paramNames, paramTypes)));
	}

	public static void assertBigNumberVariantsExist(EClass eCls, List<EOperation> currentEClssOps,
			List<String> paramNames, List<EClassifier> paramTypes) {
		Assertions.assertTrue(3 <= currentEClssOps.size(), "For eCls " + eCls.getName());
		Assertions.assertTrue(currentEClssOps.stream()
				.anyMatch((op) -> assertParamsEqual(op, paramNames,
						paramTypes.stream()
								.map((t) -> t == EcorePackage.Literals.EBIG_INTEGER ? EcorePackage.Literals.EINT : t)
								.collect(Collectors.toList()))));
		Assertions
				.assertTrue(currentEClssOps.stream()
						.anyMatch((op) -> assertParamsEqual(op, paramNames, paramTypes.stream()
								.map((t) -> t == EcorePackage.Literals.EBIG_INTEGER ? EcorePackage.Literals.ELONG : t)
								.collect(Collectors.toList()))));
		Assertions
				.assertTrue(currentEClssOps.stream()
						.anyMatch((op) -> assertParamsEqual(op, paramNames, paramTypes.stream()
								.map((t) -> t == EcorePackage.Literals.EBIG_DECIMAL ? EcorePackage.Literals.EFLOAT : t)
								.collect(Collectors.toList()))));
		Assertions
				.assertTrue(currentEClssOps.stream()
						.anyMatch((op) -> assertParamsEqual(op, paramNames, paramTypes.stream()
								.map((t) -> t == EcorePackage.Literals.EBIG_DECIMAL ? EcorePackage.Literals.EDOUBLE : t)
								.collect(Collectors.toList()))));
	}

	public static boolean assertParamsEqual(EOperation op, List<String> expectedParamNames,
			List<EClassifier> expectedParamTypes) {
		for (int i = 0; i < expectedParamNames.size(); i++) {
			var currentParam = op.getEParameters().get(i);
			if (!expectedParamNames.get(i).equals(currentParam.getName()))
				return false;
			// TODO Find out if it is possible to type check, currently the return type of
			// the op is a proxy object
//			Assertions.assertEquals(expectedParamTypes.get(i), currentParam.getEType());
		}
		return true;
	}

	public static boolean assertArrayValuedParamsEqual(EOperation op, List<String> expectedParamNames,
			List<EClassifier> expectedParamTypes) {
		for (int i = 0; i < expectedParamNames.size(); i++) {
			var currentParam = op.getEParameters().get(i);
			if (!expectedParamNames.get(i).equals(currentParam.getName()))
				return false;
			// TODO Find out if it is possible to type check, currently the return type of
			// the op is a proxy object
//			Assertions.assertEquals(expectedParamTypes.get(i), currentParam.getEType());
		}
		return true;
	}

	public static boolean assertCollectionValuedParamsEqual(EOperation op, List<String> expectedParamNames,
			List<EClassifier> expectedParamTypes) {
		for (int i = 0; i < expectedParamNames.size(); i++) {
			var currentParam = op.getEParameters().get(i);
			if (!expectedParamNames.get(i).equals(currentParam.getName()))
				return false;
			// TODO Find out if it is possible to type check, currently the return type of
			// the op is a proxy object
//			Assertions.assertEquals(expectedParamTypes.get(i), currentParam.getEType());
		}
		return true;
	}
}
