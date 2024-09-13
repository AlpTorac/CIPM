package cipm.consistency.fitests.similarity.eobject.initialiser.java.commons;

import org.emftext.language.java.commons.Commentable;

import cipm.consistency.fitests.similarity.eobject.initialiser.EObjectInitialiser;

public interface ICommentableInitialiser extends EObjectInitialiser {
	@Override
	public Commentable instantiate();

}
