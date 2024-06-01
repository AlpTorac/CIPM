package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.generics.TypeArgument;
import org.emftext.language.java.generics.TypeArgumentable;
import org.emftext.language.java.instantiations.NewConstructorCall;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.testable.ITypeArgumentableInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesTypeArguments;

public class TypeArgumentableTest extends EObjectSimilarityTest implements UsesTypeArguments {
	protected TypeArgumentable initElement(ITypeArgumentableInitialiser init, TypeArgument arg) {
		TypeArgumentable result = init.instantiate();
		init.minimalInitialisation(result);
		init.addTypeArgument(result, arg);
		return result;
	}
	
	@ParameterizedTest
	@ArgumentsSource(TypeArgumentableTestParams.class)
	public void testTypeArgument(ITypeArgumentableInitialiser init) {
		this.setResourceFileTestIdentifier("testTypeArgument");
		
		var objOne = this.initElement(init, this.createMinimalExtendsTAWithCls("cls1"));
		var objTwo = this.initElement(init, this.createMinimalSuperTAWithCls("cls2"));
		
		this.testX(objOne, objTwo, false);
	}
}