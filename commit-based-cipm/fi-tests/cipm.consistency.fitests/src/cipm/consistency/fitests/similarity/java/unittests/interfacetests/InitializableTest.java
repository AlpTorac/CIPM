package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import java.math.BigInteger;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.instantiations.Initializable;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.params.LiteralFactory;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IInitializableInitialiser;

public class InitializableTest extends EObjectSimilarityTest {
	protected Initializable initElement(IInitializableInitialiser init, Expression initVal) {
		Initializable result = init.instantiate();
		init.minimalInitialisation(result);
		init.setInitialValue(result, initVal);
		return result;
	}
	
	@ParameterizedTest
	@ArgumentsSource(InitializableTestParams.class)
	public void testInitialValue(IInitializableInitialiser init) {
		this.setResourceFileTestIdentifier("testInitialValue");
		
		var objOne = this.initElement(init, new LiteralFactory().createDecIntegerLiteral(BigInteger.valueOf(5)));
		var objTwo = this.initElement(init, new LiteralFactory().createBooleanLiteral(false));
		
		this.testX(objOne, objTwo, false);
	}
}
