package cipm.consistency.fitests.similarity.eobject.initialiser.java.statements;

import org.emftext.language.java.statements.WhileLoop;

public interface IWhileLoopInitialiser
		extends IConditionalInitialiser, IStatementInitialiser, IStatementContainerInitialiser {
	@Override
	public WhileLoop instantiate();

}
