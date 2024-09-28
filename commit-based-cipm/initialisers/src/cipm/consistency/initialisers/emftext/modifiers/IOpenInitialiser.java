package cipm.consistency.initialisers.emftext.modifiers;

import org.emftext.language.java.modifiers.Open;

import cipm.consistency.initialisers.emftext.commons.ICommentableInitialiser;

public interface IOpenInitialiser extends ICommentableInitialiser {
	@Override
	public Open instantiate();

}
