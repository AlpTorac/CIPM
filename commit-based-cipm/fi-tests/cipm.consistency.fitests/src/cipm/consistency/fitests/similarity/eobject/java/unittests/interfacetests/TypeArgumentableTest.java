package cipm.consistency.fitests.similarity.eobject.java.unittests.interfacetests;

import org.emftext.language.java.generics.GenericsPackage;
import org.emftext.language.java.generics.TypeArgument;
import org.emftext.language.java.generics.TypeArgumentable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.eobject.java.AbstractEObjectJavaSimilarityTest;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.generics.ITypeArgumentableInitialiser;
import cipm.consistency.fitests.similarity.eobject.java.unittests.UsesTypeArguments;

public class TypeArgumentableTest extends AbstractEObjectJavaSimilarityTest implements UsesTypeArguments {
	protected TypeArgumentable initElement(ITypeArgumentableInitialiser init, TypeArgument typeArg) {
		TypeArgumentable result = init.instantiate();
		Assertions.assertTrue(init.initialise(result));
		Assertions.assertTrue(init.addTypeArgument(result, typeArg));
		return result;
	}

	@ParameterizedTest
	@ArgumentsSource(TypeArgumentableTestParams.class)
	public void testTypeArgument(ITypeArgumentableInitialiser init) {
		var objOne = this.initElement(init, this.createMinimalExtendsTAWithCls("cls1"));
		var objTwo = this.initElement(init, this.createMinimalSuperTAWithCls("cls2"));

		this.testSimilarity(objOne, objTwo, GenericsPackage.Literals.TYPE_ARGUMENTABLE__TYPE_ARGUMENTS);
	}

	@ParameterizedTest
	@ArgumentsSource(TypeArgumentableTestParams.class)
	public void testTypeArgumentNullCheck(ITypeArgumentableInitialiser init) {
		this.testSimilarityNullCheck(this.initElement(init, this.createMinimalExtendsTAWithCls("cls1")), init, true,
				GenericsPackage.Literals.TYPE_ARGUMENTABLE__TYPE_ARGUMENTS);
	}
}
