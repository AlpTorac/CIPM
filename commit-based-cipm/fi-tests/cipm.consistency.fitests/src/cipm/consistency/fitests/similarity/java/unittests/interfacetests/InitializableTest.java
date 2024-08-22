package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.instantiations.Initializable;
import org.emftext.language.java.instantiations.InstantiationsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.instantiations.IInitializableInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesLiterals;

public class InitializableTest extends EObjectSimilarityTest implements UsesLiterals {
	protected Initializable initElement(IInitializableInitialiser init, Expression initVal) {
		Initializable result = init.instantiate();
		Assertions.assertTrue(init.initialise(result));
		Assertions.assertTrue(init.setInitialValue(result, initVal));
		return result;
	}

	@ParameterizedTest
	@ArgumentsSource(InitializableTestParams.class)
	public void testInitialValue(IInitializableInitialiser init) {
		this.setCurrentInitialiser(init);
		this.setResourceFileTestIdentifier("testInitialValue");

		var objOne = this.initElement(init, this.createDecimalIntegerLiteral(5));
		var objTwo = this.initElement(init, this.createBooleanLiteral(false));

		this.testSimilarity(objOne, objTwo, InstantiationsPackage.Literals.INITIALIZABLE__INITIAL_VALUE);
	}
	
	@ParameterizedTest
	@ArgumentsSource(InitializableTestParams.class)
	public void testInitialValueNull(IInitializableInitialiser init) {
		this.setCurrentInitialiser(init);
		this.setResourceFileTestIdentifier("testInitialValueNull");
		
		var objOne = this.initElement(init, this.createDecimalIntegerLiteral(5));
		var objTwo = init.instantiate();
		Assertions.assertTrue(init.initialise(objTwo));
		
		this.testSimilarity(objOne, objTwo, InstantiationsPackage.Literals.INITIALIZABLE__INITIAL_VALUE);
	}
}
