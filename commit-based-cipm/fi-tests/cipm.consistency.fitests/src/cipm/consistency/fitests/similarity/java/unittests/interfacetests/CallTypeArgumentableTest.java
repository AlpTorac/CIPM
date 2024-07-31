package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.generics.CallTypeArgumentable;
import org.emftext.language.java.generics.GenericsPackage;
import org.emftext.language.java.generics.TypeArgument;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.generics.ICallTypeArgumentableInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesTypeArguments;

public class CallTypeArgumentableTest extends EObjectSimilarityTest implements UsesTypeArguments {
	protected CallTypeArgumentable initElement(ICallTypeArgumentableInitialiser init, TypeArgument[] callTypeArgs) {
		CallTypeArgumentable result = init.instantiate();
		Assertions.assertTrue(init.initialise(result));
		Assertions.assertTrue(init.addCallTypeArguments(result, callTypeArgs));
		return result;
	}

	@ParameterizedTest
	@ArgumentsSource(CallTypeArgumentableTestParams.class)
	public void testCallTypeArguments(ICallTypeArgumentableInitialiser init) {
		this.setCurrentInitialiser(init);
		this.setResourceFileTestIdentifier("testCallTypeArguments");

		var objOne = this.initElement(init, new TypeArgument[] { this.createMinimalExtendsTAWithCls("cls1") });
		var objTwo = this.initElement(init, new TypeArgument[] { this.createMinimalSuperTAWithCls("cls2") });

		this.testSimilarity(objOne, objTwo, GenericsPackage.Literals.CALL_TYPE_ARGUMENTABLE__CALL_TYPE_ARGUMENTS);
	}
}
