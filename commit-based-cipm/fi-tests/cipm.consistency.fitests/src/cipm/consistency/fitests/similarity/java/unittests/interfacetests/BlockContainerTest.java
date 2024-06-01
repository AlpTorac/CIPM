package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.BlockContainer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IBlockContainerInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesStatements;

public class BlockContainerTest extends EObjectSimilarityTest implements UsesStatements{
	protected BlockContainer initElement(IBlockContainerInitialiser init, Block bl) {
		BlockContainer result = init.instantiate();
		init.minimalInitialisation(result);
		init.setBlock(result, bl);
		return result;
	}
	
	@ParameterizedTest
	@ArgumentsSource(BlockContainerTestParams.class)
	public void testBlock(IBlockContainerInitialiser init) {
		this.setResourceFileTestIdentifier("testBlock");
		
		var objOne = this.initElement(init, this.createMinimalBlockWithNullReturn());
		var objTwo = this.initElement(init, this.createMinimalBlockWithTrivialAssert());
		
		this.testX(objOne, objTwo, false);
	}
}