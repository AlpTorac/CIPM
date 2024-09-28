package cipm.consistency.fitests.similarity.emftext.unittests.interfacetests;

import java.util.stream.Stream;

import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.BlockContainer;
import org.emftext.language.java.statements.StatementsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cipm.consistency.fitests.similarity.emftext.AbstractEMFTextSimilarityTest;
import cipm.consistency.fitests.similarity.emftext.unittests.UsesStatements;
import cipm.consistency.initialisers.emftext.statements.IBlockContainerInitialiser;

public class BlockContainerTest extends AbstractEMFTextSimilarityTest implements UsesStatements {
	
	private static Stream<Arguments> provideArguments() {
		return AbstractEMFTextSimilarityTest.getAllInitialiserArgumentsFor(IBlockContainerInitialiser.class);
	}
	
	protected BlockContainer initElement(IBlockContainerInitialiser init, Block bl) {
		BlockContainer result = init.instantiate();
		Assertions.assertTrue(init.initialise(result));
		Assertions.assertTrue(init.setBlock(result, bl));
		return result;
	}

	@ParameterizedTest
	@MethodSource("provideArguments")
	public void testBlock(IBlockContainerInitialiser init) {
		var objOne = this.initElement(init, this.createMinimalBlockWithNullReturn());
		var objTwo = this.initElement(init, this.createMinimalBlockWithTrivialAssert());

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.BLOCK_CONTAINER__BLOCK);
	}

	@ParameterizedTest
	@MethodSource("provideArguments")
	public void testBlockNullCheck(IBlockContainerInitialiser init) {
		this.testSimilarityNullCheck(this.initElement(init, this.createMinimalBlockWithNullReturn()), init, true,
				StatementsPackage.Literals.BLOCK_CONTAINER__BLOCK);
	}
}
