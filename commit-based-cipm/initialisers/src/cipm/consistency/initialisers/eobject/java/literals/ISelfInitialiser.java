package cipm.consistency.initialisers.eobject.java.literals;

import org.emftext.language.java.literals.Self;

import cipm.consistency.initialisers.eobject.java.commons.ICommentableInitialiser;

public interface ISelfInitialiser extends ICommentableInitialiser {
	@Override
	public Self instantiate();

}
