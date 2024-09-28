package cipm.consistency.initialisers.emftext.literals;

import org.emftext.language.java.literals.Self;

import cipm.consistency.initialisers.emftext.commons.ICommentableInitialiser;

public interface ISelfInitialiser extends ICommentableInitialiser {
	@Override
	public Self instantiate();

}
