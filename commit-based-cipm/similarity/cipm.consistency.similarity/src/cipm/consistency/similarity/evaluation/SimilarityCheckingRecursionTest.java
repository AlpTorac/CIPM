package cipm.consistency.similarity.evaluation;

import java.util.List;

import org.emftext.language.java.statements.StatementsFactory;
import org.emftext.language.java.statements.StatementsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.similarity.SimilarityCheckerConfig;

public class SimilarityCheckingRecursionTest {
	/**
	 * TryBlock -> Block -> [ExpressionStatement, ExpressionStatement,
	 * ExpressionStatement, ExpressionStatement]
	 */
	@Test
	public void recursionTest_False() {
		var container = StatementsFactory.eINSTANCE.createTryBlock();
		var containerBlock = StatementsFactory.eINSTANCE.createBlock();
		container.setBlock(containerBlock);

		var est1 = StatementsFactory.eINSTANCE.createExpressionStatement();
		var est2 = StatementsFactory.eINSTANCE.createExpressionStatement();
		var est3 = StatementsFactory.eINSTANCE.createExpressionStatement();
		var est4 = StatementsFactory.eINSTANCE.createExpressionStatement();

		container.getStatements().addAll(List.of(est1, est2, est3, est4));

		Assertions.assertTrue(SimilarityCheckerConfig
				.isDerivedFeatureRelevant(StatementsPackage.Literals.EXPRESSION_STATEMENT, "predecessor"));
		Assertions.assertTrue(SimilarityCheckerConfig
				.isDerivedFeatureRelevant(StatementsPackage.Literals.EXPRESSION_STATEMENT, "successor"));
//		Assertions.assertFalse(SimilarityCheckerConfig.compare(est2, est3));
	}

	/**
	 * TryBlock -> Block -> [ExpressionStatement, ExpressionStatement,
	 * ExpressionStatement]
	 * 
	 * TryBlock -> Block -> [ExpressionStatement, ExpressionStatement,
	 * ExpressionStatement]
	 */
	@Test
	public void recursionTest_True() {
		var container1 = StatementsFactory.eINSTANCE.createTryBlock();
		var container1Block = StatementsFactory.eINSTANCE.createBlock();
		container1.setBlock(container1Block);

		var est11 = StatementsFactory.eINSTANCE.createExpressionStatement();
		var est12 = StatementsFactory.eINSTANCE.createExpressionStatement();
		var est13 = StatementsFactory.eINSTANCE.createExpressionStatement();

		container1.getStatements().addAll(List.of(est11, est12, est13));

		var container2 = StatementsFactory.eINSTANCE.createTryBlock();
		var container2Block = StatementsFactory.eINSTANCE.createBlock();
		container2.setBlock(container2Block);

		var est21 = StatementsFactory.eINSTANCE.createExpressionStatement();
		var est22 = StatementsFactory.eINSTANCE.createExpressionStatement();
		var est23 = StatementsFactory.eINSTANCE.createExpressionStatement();

		container2.getStatements().addAll(List.of(est21, est22, est23));

		Assertions.assertTrue(SimilarityCheckerConfig
				.isDerivedFeatureRelevant(StatementsPackage.Literals.EXPRESSION_STATEMENT, "predecessor"));
		Assertions.assertTrue(SimilarityCheckerConfig
				.isDerivedFeatureRelevant(StatementsPackage.Literals.EXPRESSION_STATEMENT, "successor"));
//		Assertions.assertTrue(SimilarityCheckerConfig.compare(est12, est12));
	}
}
