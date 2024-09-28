package cipm.consistency.initialisers.emftext.statements;

import org.emftext.language.java.statements.WhileLoop;

public interface IWhileLoopInitialiser
		extends IConditionalInitialiser, IStatementInitialiser, IStatementContainerInitialiser {
	@Override
	public WhileLoop instantiate();

}
