package cipm.consistency.fitests.similarity.eobject.java.unittests.interfacetests;

import org.emftext.language.java.statements.Jump;
import org.emftext.language.java.statements.JumpLabel;
import org.emftext.language.java.statements.StatementsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.eobject.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.statements.IJumpInitialiser;
import cipm.consistency.fitests.similarity.eobject.java.unittests.UsesStatements;

public class JumpTest extends EObjectSimilarityTest implements UsesStatements {
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
