package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.generics.CallTypeArgumentable;
import org.emftext.language.java.generics.TypeArgument;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.testable.ICallTypeArgumentableInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesTypeArguments;

public class CallTypeArgumentableTest extends EObjectSimilarityTest implements UsesTypeArguments {
	protected CallTypeArgumentable initElement(ICallTypeArgumentableInitialiser init, TypeArgument[] tas) {
		CallTypeArgumentable result = init.instantiate();
		init.minimalInitialisation(result);
		init.addCallTypeArguments(result, tas);
		return result;
	}
	
	@ParameterizedTest
	@ArgumentsSource(CallTypeArgumentableTestParams.class)
	public void testCallTypeArguments(ICallTypeArgumentableInitialiser init) {
		this.setResourceFileTestIdentifier("testCallTypeArguments");
		
		var objOne = this.initElement(init, new TypeArgument[] {
				this.createMinimalExtendsTAWithCls("cls1")
		});
		var objTwo = this.initElement(init, new TypeArgument[] {
				this.createMinimalSuperTAWithCls("cls2")
		});
		
		this.testX(objOne, objTwo, false);
	}
}
