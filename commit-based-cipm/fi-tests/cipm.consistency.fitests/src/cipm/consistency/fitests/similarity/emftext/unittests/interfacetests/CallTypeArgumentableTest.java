package cipm.consistency.fitests.similarity.emftext.unittests.interfacetests;

import org.emftext.language.java.generics.CallTypeArgumentable;
import org.emftext.language.java.generics.GenericsPackage;
import org.emftext.language.java.generics.TypeArgument;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.emftext.AbstractEMFTextSimilarityTest;
import cipm.consistency.fitests.similarity.emftext.unittests.UsesTypeArguments;
import cipm.consistency.initialisers.emftext.generics.ICallTypeArgumentableInitialiser;

public class CallTypeArgumentableTest extends AbstractEMFTextSimilarityTest implements UsesTypeArguments {
	protected CallTypeArgumentable initElement(ICallTypeArgumentableInitialiser init, TypeArgument[] callTypeArgs) {
		CallTypeArgumentable result = init.instantiate();
		Assertions.assertTrue(init.initialise(result));
		Assertions.assertTrue(init.addCallTypeArguments(result, callTypeArgs));
		return result;
	}

	@ParameterizedTest
	@ArgumentsSource(CallTypeArgumentableTestParams.class)
	public void testCallTypeArguments(ICallTypeArgumentableInitialiser init) {
		var objOne = this.initElement(init, new TypeArgument[] { this.createMinimalExtendsTAWithCls("cls1") });
		var objTwo = this.initElement(init, new TypeArgument[] { this.createMinimalSuperTAWithCls("cls2") });

		this.testSimilarity(objOne, objTwo, GenericsPackage.Literals.CALL_TYPE_ARGUMENTABLE__CALL_TYPE_ARGUMENTS);
	}

	@ParameterizedTest
	@ArgumentsSource(CallTypeArgumentableTestParams.class)
	public void testCallTypeArgumentsSize(ICallTypeArgumentableInitialiser init) {
		var objOne = this.initElement(init, new TypeArgument[] { this.createMinimalExtendsTAWithCls("cls1"),
				this.createMinimalExtendsTAWithCls("cls2") });
		var objTwo = this.initElement(init, new TypeArgument[] { this.createMinimalExtendsTAWithCls("cls1") });

		this.testSimilarity(objOne, objTwo, GenericsPackage.Literals.CALL_TYPE_ARGUMENTABLE__CALL_TYPE_ARGUMENTS);
	}

	@ParameterizedTest
	@ArgumentsSource(CallTypeArgumentableTestParams.class)
	public void testCallTypeArgumentsNullCheck(ICallTypeArgumentableInitialiser init) {
		this.testSimilarityNullCheck(
				this.initElement(init, new TypeArgument[] { this.createMinimalExtendsTAWithCls("cls1") }), init, true,
				GenericsPackage.Literals.CALL_TYPE_ARGUMENTABLE__CALL_TYPE_ARGUMENTS);
	}
}
