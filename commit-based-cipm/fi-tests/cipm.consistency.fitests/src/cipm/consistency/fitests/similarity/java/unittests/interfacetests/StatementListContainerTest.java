package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.operators.Operator;
import org.emftext.language.java.operators.UnaryOperator;
import org.emftext.language.java.statements.ExpressionStatement;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementListContainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.UnaryExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.operators.AssignmentMinusInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.operators.AssignmentPlusInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.operators.ComplementInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.operators.MinusMinusInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.operators.NegateInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.operators.PlusPlusInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.AssertInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.EmptyStatementInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.ExpressionStatementInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.IExpressionStatementInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.ReturnInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.ThrowInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IStatementListContainerInitialiser;

public class StatementListContainerTest extends EObjectSimilarityTest {
	private IExpressionStatementInitialiser exprStInit;
	private final UnaryOperator[] ops = new UnaryOperator[] {
			new NegateInitialiser().instantiate(),
			new ComplementInitialiser().instantiate()
	};
	private ExpressionStatement[] exprStArr;
	
	@BeforeEach
	@Override
	public void setUp() {
		this.setResourceFileTestPrefix(StatementListContainerTest.class.getSimpleName());
		
		exprStInit = new ExpressionStatementInitialiser();
		var unaryExprInit = new UnaryExpressionInitialiser();
		
		var opLen = this.ops.length;
		this.exprStArr = new ExpressionStatement[opLen];
		for (int i = 0; i < opLen; i++) {
			var op = this.ops[i];
			ExpressionStatement entry = exprStInit.instantiate();
			exprStInit.minimalInitialisation(entry);
			
			var expr = unaryExprInit.instantiate();
			unaryExprInit.minimalInitialisation(expr);
			unaryExprInit.addOperator(expr, op);
			
			this.exprStArr[i] = entry;
			exprStInit.setExpression(entry, expr);
		}
		
		super.setUp();
	}
	
	protected StatementListContainer initElement(IStatementListContainerInitialiser initialiser,
			Statement[] sts) {
		
		StatementListContainer result = initialiser.instantiate();
//		initialiser.minimalInitialisationWithContainer(result);
		
		if (sts != null) {
			for (var st : sts) {
				initialiser.addStatement(result, st);
			}
		}
		
		return result;
	}
	
	protected ExpressionStatement cloneStAt(int index) {
		return this.exprStInit.clone(this.exprStArr[index]);
	}
	
	@ParameterizedTest
	@ArgumentsSource(StatementListContainerTestParams.class)
	public void testStatements(IStatementListContainerInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testStatements");
		
		var objOne = this.initElement(initialiser, new Statement[] {
				this.cloneStAt(0), this.cloneStAt(1)
		});
		var objTwo = this.initElement(initialiser, new Statement[] {
				this.cloneStAt(1), this.cloneStAt(0)
		});
		
		// TODO: Replace last parameter
		this.testX(objOne, objTwo, false);
	}
}
