package cipm.consistency.fitests.similarity.emftext.unittests.interfacetests;

import org.emftext.language.java.statements.Jump;
import org.emftext.language.java.statements.JumpLabel;
import org.emftext.language.java.statements.StatementsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.emftext.AbstractEMFTextSimilarityTest;
import cipm.consistency.fitests.similarity.emftext.unittests.UsesStatements;
import cipm.consistency.initialisers.emftext.statements.IJumpInitialiser;

public class JumpTest extends AbstractEMFTextSimilarityTest implements UsesStatements {
	protected Jump initElement(IJumpInitialiser init, JumpLabel jl) {
		Jump result = init.instantiate();
		Assertions.assertTrue(init.setTarget(result, jl));
		return result;
	}

	@ParameterizedTest
	@ArgumentsSource(JumpTestParams.class)
	public void testTarget(IJumpInitialiser init) {
		var objOne = this.initElement(init, this.createMinimalJLToNullReturn("jl1"));
		var objTwo = this.initElement(init, this.createMinimalJLToTrivialAssert("jl2"));

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.JUMP__TARGET);
	}

	@ParameterizedTest
	@ArgumentsSource(JumpTestParams.class)
	public void testTargetNullCheck(IJumpInitialiser init) {
		this.testSimilarityNullCheck(this.initElement(init, this.createMinimalJLToNullReturn("jl1")), init, false,
				StatementsPackage.Literals.JUMP__TARGET);
	}
}
