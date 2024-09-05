package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.BlockContainer;
import org.emftext.language.java.statements.StatementsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.statements.IBlockContainerInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesStatements;

public class BlockContainerTest extends EObjectSimilarityTest implements UsesStatements {
	protected BlockContainer initElement(IBlockContainerInitialiser init, Block bl) {
		BlockContainer result = init.instantiate();
		Assertions.assertTrue(init.initialise(result));
		Assertions.assertTrue(init.setBlock(result, bl));
		return result;
	}

	@ParameterizedTest
	@ArgumentsSource(BlockContainerTestParams.class)
	public void testBlock(IBlockContainerInitialiser init) {
		this.setCurrentInitialiser(init);
		var objOne = this.initElement(init, this.createMinimalBlockWithNullReturn());
		var objTwo = this.initElement(init, this.createMinimalBlockWithTrivialAssert());

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.BLOCK_CONTAINER__BLOCK);
	}

	@ParameterizedTest
	@ArgumentsSource(BlockContainerTestParams.class)
	public void testBlockNullCheck(IBlockContainerInitialiser init) {
		this.setCurrentInitialiser(init);
		var objOne = this.initElement(init, this.createMinimalBlockWithNullReturn());

		this.testSimilarityNullCheck(objOne, init, true,
				StatementsPackage.Literals.BLOCK_CONTAINER__BLOCK);
	}
}
