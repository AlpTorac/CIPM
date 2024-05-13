package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import java.math.BigInteger;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.references.Argumentable;
import org.emftext.language.java.types.Type;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.params.LiteralFactory;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IArgumentableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.types.DoubleInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.types.IntInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.types.VoidInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesConcreteClassifiers;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class ArgumentableTest extends EObjectSimilarityTest implements UsesConcreteClassifiers, UsesExpressions {
	protected Argumentable initElement(IArgumentableInitialiser init, Expression[] args, Type[] types) {
		Argumentable result = init.instantiate();
		init.minimalInitialisation(result);
		init.addArguments(result, args);
		init.addArgumentTypes(result, types);
		return result;
	}
	
	@ParameterizedTest
	@ArgumentsSource(ArgumentableTestParams.class)
	public void testArguments(IArgumentableInitialiser init) {
		this.setResourceFileTestIdentifier("testArguments");
		
		var objOne = this.initElement(init, new Expression[] {
				new LiteralFactory().createDecIntegerLiteral(BigInteger.ONE)
		}, null);
		var objTwo = this.initElement(init, new Expression[] {
				new LiteralFactory().createDecIntegerLiteral(BigInteger.ZERO)
		}, null);
		
		this.testX(objOne, objTwo, false);
	}
	
	@ParameterizedTest
	@ArgumentsSource(ArgumentableTestParams.class)
	public void testArgumentTypes(IArgumentableInitialiser init) {
		this.setResourceFileTestIdentifier("testArgumentTypes");
		
		var objOne = this.initElement(init, null, new Type[] {
				new IntInitialiser().instantiate()
		});
		var objTwo = this.initElement(init, null, new Type[] {
				new VoidInitialiser().instantiate()
		});
		
		this.testX(objOne, objTwo, false);
	}
}
