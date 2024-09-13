package cipm.consistency.fitests.similarity.eobject.initialiser.java.statements;

import org.emftext.language.java.statements.ForLoopInitializer;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.commons.ICommentableInitialiser;

public interface IForLoopInitializerInitialiser extends ICommentableInitialiser {
	@Override
	public ForLoopInitializer instantiate();

}
