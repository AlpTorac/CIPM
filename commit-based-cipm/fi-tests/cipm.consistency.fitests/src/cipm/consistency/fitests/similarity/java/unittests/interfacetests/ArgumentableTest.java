package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.references.Argumentable;
import org.emftext.language.java.references.ReferencesPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.references.IArgumentableInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesConcreteClassifiers;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;
import cipm.consistency.fitests.similarity.java.unittests.UsesLiterals;

public class ArgumentableTest extends EObjectSimilarityTest
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
		this.setCurrentInitialiser(init);
		this.setResourceFileTestIdentifier("testArguments");

		var objOne = this.initElement(init, new Expression[] { this.createDecimalIntegerLiteral(1) });
		var objTwo = this.initElement(init, new Expression[] { this.createDecimalIntegerLiteral(0) });

		this.testSimilarity(objOne, objTwo, ReferencesPackage.Literals.ARGUMENTABLE__ARGUMENTS);
	}

	@ParameterizedTest
	@ArgumentsSource(ArgumentableTestParams.class)
	public void testArgumentsSize(IArgumentableInitialiser init) {
		this.setCurrentInitialiser(init);
		this.setResourceFileTestIdentifier("testArgumentsSize");

		var objOne = this.initElement(init,
				new Expression[] { this.createDecimalIntegerLiteral(1), this.createDecimalIntegerLiteral(1) });
		var objTwo = this.initElement(init, new Expression[] { this.createDecimalIntegerLiteral(1) });

		this.testSimilarity(objOne, objTwo, ReferencesPackage.Literals.ARGUMENTABLE__ARGUMENTS);
	}

	@ParameterizedTest
	@ArgumentsSource(ArgumentableTestParams.class)
	public void testArgumentsNullCheck(IArgumentableInitialiser init) {
		this.setCurrentInitialiser(init);
		this.setResourceFileTestIdentifier("testArgumentsNullCheck");

		var objOne = this.initElement(init, new Expression[] { this.createDecimalIntegerLiteral(1) });
		var objTwo = init.instantiate();
		Assertions.assertTrue(init.initialise(objTwo));

		this.testSimilarity(objOne, objTwo, ReferencesPackage.Literals.ARGUMENTABLE__ARGUMENTS);
	}
}
