package cipm.consistency.initialisers.eobject.java.modifiers;

import org.emftext.language.java.modifiers.Open;

import cipm.consistency.initialisers.eobject.java.commons.ICommentableInitialiser;

public interface IOpenInitialiser extends ICommentableInitialiser {
	@Override
	public Open instantiate();

}
