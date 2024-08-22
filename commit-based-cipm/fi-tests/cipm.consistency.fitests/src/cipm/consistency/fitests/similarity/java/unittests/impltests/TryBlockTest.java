package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.CatchBlock;
import org.emftext.language.java.statements.StatementsPackage;
import org.emftext.language.java.statements.TryBlock;
import org.emftext.language.java.variables.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.statements.TryBlockInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesCatchBlocks;
import cipm.consistency.fitests.similarity.java.unittests.UsesLocalVariables;
import cipm.consistency.fitests.similarity.java.unittests.UsesStatements;

public class TryBlockTest extends EObjectSimilarityTest implements UsesCatchBlocks, UsesStatements, UsesLocalVariables {
	protected TryBlock initElement(Resource[] ress, CatchBlock[] catchBlocks, Block finallyBlock) {
		var tbInit = new TryBlockInitialiser();
		var tb = tbInit.instantiate();
		Assertions.assertTrue(tbInit.addResources(tb, ress));
		Assertions.assertTrue(tbInit.addCatchBlocks(tb, catchBlocks));
		Assertions.assertTrue(tbInit.setFinallyBlock(tb, finallyBlock));
		return tb;
	}

	@Test
	public void testResource() {
		this.setResourceFileTestIdentifier("testResource");

		var objOne = this.initElement(new Resource[] { this.createMinimalLV("lv1") }, null, null);
		var objTwo = this.initElement(new Resource[] { this.createMinimalLV("lv2") }, null, null);

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.TRY_BLOCK__RESOURCES);
	}
	
	@Test
	public void testResourceNull() {
		this.setResourceFileTestIdentifier("testResourceNull");
		
		var objOne = this.initElement(new Resource[] { this.createMinimalLV("lv1") }, null, null);
		var objTwo = new TryBlockInitialiser().instantiate();
		
		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.TRY_BLOCK__RESOURCES);
	}

	@Test
	public void testCatchBlock() {
		this.setResourceFileTestIdentifier("testCatchBlock");

		var objOne = this.initElement(null, new CatchBlock[] { this.createMinimalCB("p1", "t1") }, null);
		var objTwo = this.initElement(null, new CatchBlock[] { this.createMinimalCB("p2", "t2") }, null);

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.TRY_BLOCK__CATCH_BLOCKS);
	}
	
	@Test
	public void testCatchBlockNull() {
		this.setResourceFileTestIdentifier("testCatchBlockNull");
		
		var objOne = this.initElement(null, new CatchBlock[] { this.createMinimalCB("p1", "t1") }, null);
		var objTwo = new TryBlockInitialiser().instantiate();
		
		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.TRY_BLOCK__CATCH_BLOCKS);
	}

	@Test
	public void testFinallyBlock() {
		this.setResourceFileTestIdentifier("testFinallyBlock");

		var objOne = this.initElement(null, null, this.createMinimalBlockWithNullReturn());
		var objTwo = this.initElement(null, null, this.createMinimalBlockWithTrivialAssert());

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.TRY_BLOCK__FINALLY_BLOCK);
	}
	
	@Test
	public void testFinallyBlockNull() {
		this.setResourceFileTestIdentifier("testFinallyBlockNull");
		
		var objOne = this.initElement(null, null, this.createMinimalBlockWithNullReturn());
		var objTwo = new TryBlockInitialiser().instantiate();
		
		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.TRY_BLOCK__FINALLY_BLOCK);
	}
}
