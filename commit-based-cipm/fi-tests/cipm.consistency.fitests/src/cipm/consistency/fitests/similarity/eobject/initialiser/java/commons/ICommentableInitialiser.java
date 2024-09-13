package cipm.consistency.fitests.similarity.eobject.initialiser.java.commons;

import org.emftext.language.java.commons.Commentable;

import cipm.consistency.fitests.similarity.eobject.initialiser.IEObjectInitialiser;

public interface ICommentableInitialiser extends IEObjectInitialiser {
	@Override
	public Commentable instantiate();

}
