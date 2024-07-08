package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.Assert;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.JumpLabel;
import org.emftext.language.java.statements.Return;
import org.emftext.language.java.statements.Statement;

import cipm.consistency.fitests.similarity.java.initialiser.params.LiteralFactory;
import cipm.consistency.fitests.similarity.java.initialiser.statements.AssertInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.BlockInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.JumpLabelInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.ReturnInitialiser;

public interface UsesStatements {
	public default Return createMinimalNullReturn() {
		return this.createMinimalReturn(new LiteralFactory().createNullLiteral());
	}
	
	public default Return createMinimalReturn(Expression returnExpr) {
		var init = new ReturnInitialiser();
		Return result = init.instantiate();
		init.setReturnValue(result, returnExpr);
		return result;
	}
	
	public default Assert createMinimalTrivialAssert() {
		var init = new AssertInitialiser();
		Assert result = init.instantiate();
		init.setCondition(result, new LiteralFactory().createBooleanLiteral(true));
		return result;
	}
	
	public default JumpLabel createMinimalJL(Statement target) {
		var init = new JumpLabelInitialiser();
		JumpLabel result = init.instantiate();
		init.setStatement(result, target);
		return result;
	}
	
	public default JumpLabel createMinimalJLToNullReturn() {
		return this.createMinimalJL(this.createMinimalNullReturn());
	}
	
	public default JumpLabel createMinimalJLToTrivialAssert() {
		return this.createMinimalJL(this.createMinimalTrivialAssert());
	}
	
	public default JumpLabel createMinimalJLToNullReturn(String jlName) {
		var jl = this.createMinimalJL(this.createMinimalNullReturn());
		var init = new JumpLabelInitialiser();
		init.setName(jl, jlName);
		return jl;
	}
	
	public default JumpLabel createMinimalJLToTrivialAssert(String jlName) {
		var jl = this.createMinimalJL(this.createMinimalTrivialAssert());
		var init = new JumpLabelInitialiser();
		init.setName(jl, jlName);
		return jl;
	}
	
	public default Block createMinimalBlock(Statement[] sts) {
		var init = new BlockInitialiser();
		Block result = init.instantiate();
		init.addStatements(result, sts);
		return result;
	}
	
	public default Block createMinimalBlockWithNullReturn() {
		return this.createMinimalBlock(new Statement[] {this.createMinimalNullReturn()});
	}
	
	public default Block createMinimalBlockWithTrivialAssert() {
		return this.createMinimalBlock(new Statement[] {this.createMinimalTrivialAssert()});
	}
}
