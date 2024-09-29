package cipm.consistency.initialisers.emftext.commons;

import org.emftext.language.java.commons.Commentable;

import cipm.consistency.initialisers.emftext.IEMFTextEObjectInitialiser;

public interface ICommentableInitialiser extends IEMFTextEObjectInitialiser {
	@Override
	public Commentable instantiate();

}
