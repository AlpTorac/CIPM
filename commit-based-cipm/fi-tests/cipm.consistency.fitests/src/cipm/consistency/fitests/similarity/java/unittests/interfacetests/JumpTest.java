package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.statements.Jump;
import org.emftext.language.java.statements.JumpLabel;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IJumpInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesStatements;

public class JumpTest extends EObjectSimilarityTest implements UsesStatements {
	protected Jump initElement(IJumpInitialiser init, JumpLabel jl) {
		Jump result = init.instantiate();
		init.minimalInitialisation(result);
		init.setTarget(result, jl);
		return result;
	}
	
	@ParameterizedTest
	@ArgumentsSource(JumpTestParams.class)
	public void testTarget(IJumpInitialiser init) {
		this.setResourceFileTestIdentifier("testTarget");
		
		var objOne = this.initElement(init, this.createMinimalJLToNullReturn());
		var objTwo = this.initElement(init, this.createMinimalJLToTrivialAssert());
		
		this.testX(objOne, objTwo, false);
	}
}