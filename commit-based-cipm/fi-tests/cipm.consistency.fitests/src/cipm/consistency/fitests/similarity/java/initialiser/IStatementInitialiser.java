package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.statements.Statement;

public interface IStatementInitialiser extends ICommentableInitialiser {
	@Override
	public Statement instantiate();
	
	@Override
	public default Statement minimalInstantiation() {
		return (Statement) ICommentableInitialiser.super.minimalInstantiation();
	}
}

/*
 * TODO: Implement remaining statements
 * 
	Assert
	Condition
	EmptyStatement
	ExpressionStatement
	ForEachLoop
	ForLoop
	Jump
	JumpLabel
	LocalVariableStatement
	Return
	Switch
	SynchronizedBlock
	Throw
	TryBlock
	WhileLoop, DoWhileLoop
	YieldStatement
 */