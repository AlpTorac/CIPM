package cipm.consistency.fitests.similarity.java.eobject.initialiser.commons;

import org.emftext.language.java.commons.Commentable;

import cipm.consistency.fitests.similarity.java.eobject.initialiser.EObjectInitialiser;

public interface ICommentableInitialiser extends EObjectInitialiser {
	@Override
	public Commentable instantiate();

}
