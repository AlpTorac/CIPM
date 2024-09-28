package cipm.consistency.initialisers.eobject.java.statements;

import org.emftext.language.java.statements.ForLoopInitializer;

import cipm.consistency.initialisers.eobject.java.commons.ICommentableInitialiser;

public interface IForLoopInitializerInitialiser extends ICommentableInitialiser {
	@Override
	public ForLoopInitializer instantiate();

}
