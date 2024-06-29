package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import java.math.BigInteger;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.references.Argumentable;
import org.emftext.language.java.references.ReferencesPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.params.LiteralFactory;
import cipm.consistency.fitests.similarity.java.initialiser.references.IArgumentableInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesConcreteClassifiers;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class ArgumentableTest extends EObjectSimilarityTest implements UsesConcreteClassifiers, UsesExpressions {
	protected Argumentable initElement(IArgumentableInitialiser init, Expression[] args) {
		Argumentable result = init.instantiate();
		Assertions.assertTrue(init.minimalInitialisation(result));
		Assertions.assertTrue(init.addArguments(result, args));
		return result;
	}
	
	@ParameterizedTest
	@ArgumentsSource(ArgumentableTestParams.class)
	public void testArguments(IArgumentableInitialiser init) {
		this.setResourceFileTestIdentifier("testArguments");
		
		var objOne = this.initElement(init, new Expression[] {
				new LiteralFactory().createDecIntegerLiteral(BigInteger.ONE)
		});
		var objTwo = this.initElement(init, new Expression[] {
				new LiteralFactory().createDecIntegerLiteral(BigInteger.ZERO)
		});
		
		this.testX(objOne, objTwo, ReferencesPackage.Literals.ARGUMENTABLE__ARGUMENTS);
	}
}
