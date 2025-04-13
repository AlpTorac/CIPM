package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.CatchBlock;
import org.emftext.language.java.statements.StatementsPackage;
import org.emftext.language.java.statements.TryBlock;
import org.emftext.language.java.statements.impl.BlockImpl;
import org.emftext.language.java.variables.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesCatchBlocks;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesLocalVariables;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesStatements;
import cipm.consistency.initialisers.jamopp.statements.TryBlockInitialiser;

public class TryBlockTest extends AbstractJaMoPPSimilarityTest
		implements UsesCatchBlocks, UsesStatements, UsesLocalVariables {
	private Resource res1;
	private Resource res2;
	private CatchBlock cb1;
	private CatchBlock cb2;
	private Block fb1;
	private Block fb2;

	protected TryBlock initElement(Resource[] ress, CatchBlock[] catchBlocks, Block finallyBlock) {
		var tbInit = new TryBlockInitialiser();
		var tb = tbInit.instantiate();
		Assertions.assertTrue(tbInit.addResources(tb, ress));
		Assertions.assertTrue(tbInit.addCatchBlocks(tb, catchBlocks));
		Assertions.assertTrue(tbInit.setFinallyBlock(tb, finallyBlock));
		return tb;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		res1 = this.createMinimalLV("lv1");
		res2 = this.createMinimalLV("lv2");
		Assertions.assertFalse(this.isSimilar(res1, res2));

		cb1 = this.createMinimalCB("p1", "t1");
		cb2 = this.createMinimalCB("p2", "t2");
		Assertions.assertFalse(this.isSimilar(cb1, cb2));

		fb1 = this.createMinimalBlock();
		/*
		 * Since there is currently no way to make Block instances different, use an
		 * anonymous class instance to force difference.
		 */
		fb2 = new BlockImpl() {
		};
		Assertions.assertFalse(this.isSimilar(fb1, fb2));
	}

	@Test
	public void testResource() {
		var objOne = this.initElement(new Resource[] { this.cloneEObjWithContainers(res1) }, null, null);
		var objTwo = this.initElement(new Resource[] { this.cloneEObjWithContainers(res2) }, null, null);

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.TRY_BLOCK__RESOURCES);
	}

	@Test
	public void testResourceSize() {
		var objOne = this.initElement(
				new Resource[] { this.cloneEObjWithContainers(res1), this.cloneEObjWithContainers(res2) }, null, null);
		var objTwo = this.initElement(new Resource[] { this.cloneEObjWithContainers(res1) }, null, null);

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.TRY_BLOCK__RESOURCES);
	}

	@Test
	public void testResourcePosition() {
		var objOne = this.initElement(
				new Resource[] { this.cloneEObjWithContainers(res1), this.cloneEObjWithContainers(res2) }, null, null);
		var objTwo = this.initElement(
				new Resource[] { this.cloneEObjWithContainers(res2), this.cloneEObjWithContainers(res1) }, null, null);

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.TRY_BLOCK__RESOURCES);
	}

	@Test
	public void testResourceDuplication() {
		var objOne = this.initElement(
				new Resource[] { this.cloneEObjWithContainers(res1), this.cloneEObjWithContainers(res1) }, null, null);
		var objTwo = this.initElement(new Resource[] { this.cloneEObjWithContainers(res1) }, null, null);

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.TRY_BLOCK__RESOURCES);
	}

	@Test
	public void testResourceNullCheck() {
		this.testSimilarityNullCheck(
				this.initElement(new Resource[] { this.cloneEObjWithContainers(res1) }, null, null),
				new TryBlockInitialiser(), false, StatementsPackage.Literals.TRY_BLOCK__RESOURCES);
	}

	@Test
	public void testCatchBlock() {
		var objOne = this.initElement(null, new CatchBlock[] { this.cloneEObjWithContainers(cb1) }, null);
		var objTwo = this.initElement(null, new CatchBlock[] { this.cloneEObjWithContainers(cb2) }, null);

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.TRY_BLOCK__CATCH_BLOCKS);
	}

	@Test
	public void testCatchBlockSize() {
		var objOne = this.initElement(null,
				new CatchBlock[] { this.cloneEObjWithContainers(cb1), this.cloneEObjWithContainers(cb2) }, null);
		var objTwo = this.initElement(null, new CatchBlock[] { this.cloneEObjWithContainers(cb1) }, null);

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.TRY_BLOCK__CATCH_BLOCKS);
	}

	@Test
	public void testCatchBlockPosition() {
		var objOne = this.initElement(null,
				new CatchBlock[] { this.cloneEObjWithContainers(cb1), this.cloneEObjWithContainers(cb2) }, null);
		var objTwo = this.initElement(null,
				new CatchBlock[] { this.cloneEObjWithContainers(cb2), this.cloneEObjWithContainers(cb1) }, null);

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.TRY_BLOCK__CATCH_BLOCKS);
	}

	@Test
	public void testCatchBlockDuplication() {
		var objOne = this.initElement(null,
				new CatchBlock[] { this.cloneEObjWithContainers(cb1), this.cloneEObjWithContainers(cb1) }, null);
		var objTwo = this.initElement(null, new CatchBlock[] { this.cloneEObjWithContainers(cb1) }, null);

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.TRY_BLOCK__CATCH_BLOCKS);
	}

	@Test
	public void testCatchBlockNullCheck() {
		this.testSimilarityNullCheck(
				this.initElement(null, new CatchBlock[] { this.cloneEObjWithContainers(cb1) }, null),
				new TryBlockInitialiser(), false, StatementsPackage.Literals.TRY_BLOCK__CATCH_BLOCKS);
	}

	@Test
	public void testFinallyBlock() {
		var objOne = this.initElement(null, null, this.cloneEObjWithContainers(fb1));
		var objTwo = this.initElement(null, null, this.cloneEObjWithContainers(fb2));

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.TRY_BLOCK__FINALLY_BLOCK);
	}

	@Test
	public void testFinallyBlockNullCheck() {
		this.testSimilarityNullCheck(this.initElement(null, null, this.cloneEObjWithContainers(fb1)),
				new TryBlockInitialiser(), false, StatementsPackage.Literals.TRY_BLOCK__FINALLY_BLOCK);
	}
}
