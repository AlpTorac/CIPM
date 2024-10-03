package cipm.consistency.initialisers.jamopp.commons;

import org.emftext.language.java.commons.Commentable;

import cipm.consistency.initialisers.jamopp.IEMFTextEObjectInitialiser;

public interface ICommentableInitialiser extends IEMFTextEObjectInitialiser {
	@Override
	public Commentable instantiate();

}
