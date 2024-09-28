package cipm.consistency.initialisers.eobject.java.commons;

import org.emftext.language.java.commons.Commentable;

import cipm.consistency.initialisers.eobject.IEObjectInitialiser;

public interface ICommentableInitialiser extends IEObjectInitialiser {
	@Override
	public Commentable instantiate();

}
