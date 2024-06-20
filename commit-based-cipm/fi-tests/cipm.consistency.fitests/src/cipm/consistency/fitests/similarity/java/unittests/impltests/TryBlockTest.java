package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.CatchBlock;
import org.emftext.language.java.statements.TryBlock;
import org.emftext.language.java.variables.Resource;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.statements.TryBlockInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesCatchBlocks;
import cipm.consistency.fitests.similarity.java.unittests.UsesLocalVariables;
import cipm.consistency.fitests.similarity.java.unittests.UsesStatements;

public class TryBlockTest extends EObjectSimilarityTest implements UsesCatchBlocks,
UsesStatements, UsesLocalVariables {
	protected TryBlock initElement(Resource[] ress, CatchBlock[] catchBlocks, CatchBlock[] catcheBlocks, Block block) {
		var tbInit = new TryBlockInitialiser();
		var tb = tbInit.instantiate();
		tbInit.minimalInitialisation(tb);
		tbInit.addResources(tb, ress);
		tbInit.addCatchBlocks(tb, catchBlocks);
		tbInit.addCatcheBlocks(tb, catcheBlocks);
		tbInit.setFinallyBlock(tb, block);
		return tb;
	}
	
	@Test
	public void testResource() {
		this.setResourceFileTestIdentifier("testResource");
		
		var objOne = this.initElement(new Resource[] {this.createMinimalLV("lv1")}, null, null, null);
		var objTwo = this.initElement(new Resource[] {this.createMinimalLV("lv2")}, null, null, null);
		
		this.testX(objOne, objTwo, false);
	}
	
	@Test
	public void testCatchBlock() {
		this.setResourceFileTestIdentifier("testCatchBlock");
		
		var objOne = this.initElement(null, new CatchBlock[] {this.createMinimalCB("p1", "t1")}, null, null);
		var objTwo = this.initElement(null, new CatchBlock[] {this.createMinimalCB("p2", "t2")}, null, null);
		
		this.testX(objOne, objTwo, false);
	}
	
	@Test
	public void testCatcheBlock() {
		this.setResourceFileTestIdentifier("testCatcheBlock");
		
		var objOne = this.initElement(null, null, new CatchBlock[] {this.createMinimalCB("p1", "t1")}, null);
		var objTwo = this.initElement(null, null, new CatchBlock[] {this.createMinimalCB("p2", "t2")}, null);
		
		this.testX(objOne, objTwo, false);
	}
	
	@Test
	public void testFinallyBlock() {
		this.setResourceFileTestIdentifier("testFinallyBlock");
		
		var objOne = this.initElement(null, null, null, this.createMinimalBlockWithNullReturn());
		var objTwo = this.initElement(null, null, null, this.createMinimalBlockWithTrivialAssert());
		
		this.testX(objOne, objTwo, false);
	}
}
