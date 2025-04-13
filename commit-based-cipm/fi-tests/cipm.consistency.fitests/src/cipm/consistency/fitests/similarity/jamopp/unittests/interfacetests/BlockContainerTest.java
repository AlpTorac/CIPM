package cipm.consistency.fitests.similarity.jamopp.unittests.interfacetests;

import java.util.stream.Stream;

import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.BlockContainer;
import org.emftext.language.java.statements.StatementsPackage;
import org.emftext.language.java.statements.impl.BlockImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesStatements;
import cipm.consistency.initialisers.jamopp.statements.IBlockContainerInitialiser;

public class BlockContainerTest extends AbstractJaMoPPSimilarityTest implements UsesStatements {
	private Block bl1;
	private Block bl2;

	private static Stream<Arguments> provideArguments() {
		return AbstractJaMoPPSimilarityTest.getAllInitialiserArgumentsFor(IBlockContainerInitialiser.class);
	}

	protected BlockContainer initElement(IBlockContainerInitialiser init, Block bl) {
		BlockContainer result = init.instantiate();
		Assertions.assertTrue(init.initialise(result));
		Assertions.assertTrue(init.setBlock(result, bl));
		return result;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		bl1 = this.createMinimalBlock();
		/*
		 * Since there is currently no way to make different Block instances, use an
		 * anonymous class to force difference.
		 */
		bl2 = new BlockImpl() {
		};
		Assertions.assertFalse(this.isSimilar(bl1, bl2));
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testBlock(IBlockContainerInitialiser init, String displayName) {
		var objOne = this.initElement(init, this.cloneEObjWithContainers(bl1));
		var objTwo = this.initElement(init, this.cloneEObjWithContainers(bl2));

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.BLOCK_CONTAINER__BLOCK);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testBlockNullCheck(IBlockContainerInitialiser init, String displayName) {
		this.testSimilarityNullCheck(this.initElement(init, this.cloneEObjWithContainers(bl1)), init, true,
				StatementsPackage.Literals.BLOCK_CONTAINER__BLOCK);
	}
}
