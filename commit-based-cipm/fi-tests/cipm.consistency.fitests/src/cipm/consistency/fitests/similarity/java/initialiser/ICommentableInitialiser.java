package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.commons.Commentable;

public interface ICommentableInitialiser extends EObjectInitialiser {
	@Override
	public Commentable instantiate();
}
