package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.WhileLoop;

public interface IWhileLoopInitialiser
		extends IConditionalInitialiser, IStatementInitialiser, IStatementContainerInitialiser {
	@Override
	public WhileLoop instantiate();

}
