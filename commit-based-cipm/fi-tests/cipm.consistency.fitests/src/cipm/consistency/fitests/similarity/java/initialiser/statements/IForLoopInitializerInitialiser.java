package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.ForLoopInitializer;

import cipm.consistency.fitests.similarity.java.initialiser.commons.ICommentableInitialiser;

public interface IForLoopInitializerInitialiser extends ICommentableInitialiser {
	@Override
	public ForLoopInitializer instantiate();

}
