package cipm.consistency.fitests.similarity.emftext.unittests.interfacetests;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.references.Argumentable;
import org.emftext.language.java.references.ReferencesPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.emftext.AbstractEMFTextSimilarityTest;
import cipm.consistency.fitests.similarity.emftext.unittests.UsesConcreteClassifiers;
import cipm.consistency.fitests.similarity.emftext.unittests.UsesExpressions;
import cipm.consistency.fitests.similarity.emftext.unittests.UsesLiterals;
import cipm.consistency.initialisers.emftext.references.IArgumentableInitialiser;

public class ArgumentableTest extends AbstractEMFTextSimilarityTest
		implements UsesConcreteClassifiers, UsesExpressions, UsesLiterals {
	protected Argumentable initElement(IArgumentableInitialiser init, Expression[] args) {
		var result = init.instantiate();
		Assertions.assertTrue(init.initialise(result));
		Assertions.assertTrue(init.addArguments(result, args));
		return result;
	}

	@ParameterizedTest
	@ArgumentsSource(ArgumentableTestParams.class)
	public void testArguments(IArgumentableInitialiser init) {
		var objOne = this.initElement(init, new Expression[] { this.createDecimalIntegerLiteral(1) });
		var objTwo = this.initElement(init, new Expression[] { this.createDecimalIntegerLiteral(0) });

		this.testSimilarity(objOne, objTwo, ReferencesPackage.Literals.ARGUMENTABLE__ARGUMENTS);
	}

	@ParameterizedTest
	@ArgumentsSource(ArgumentableTestParams.class)
	public void testArgumentsSize(IArgumentableInitialiser init) {
		var objOne = this.initElement(init,
				new Expression[] { this.createDecimalIntegerLiteral(1), this.createDecimalIntegerLiteral(2) });
		var objTwo = this.initElement(init, new Expression[] { this.createDecimalIntegerLiteral(1) });

		this.testSimilarity(objOne, objTwo, ReferencesPackage.Literals.ARGUMENTABLE__ARGUMENTS);
	}

	@ParameterizedTest
	@ArgumentsSource(ArgumentableTestParams.class)
	public void testArgumentsNullCheck(IArgumentableInitialiser init) {
		this.testSimilarityNullCheck(this.initElement(init, new Expression[] { this.createDecimalIntegerLiteral(1) }),
				init, true, ReferencesPackage.Literals.ARGUMENTABLE__ARGUMENTS);
	}
}
