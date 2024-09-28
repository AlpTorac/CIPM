package cipm.consistency.initialisers.emftext.statements;

import org.emftext.language.java.statements.ForLoopInitializer;

import cipm.consistency.initialisers.emftext.commons.ICommentableInitialiser;

public interface IForLoopInitializerInitialiser extends ICommentableInitialiser {
	@Override
	public ForLoopInitializer instantiate();

}
