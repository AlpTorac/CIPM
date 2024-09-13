package cipm.consistency.fitests.similarity.eobject.initialiser.java.literals;

import org.emftext.language.java.literals.Self;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.commons.ICommentableInitialiser;

public interface ISelfInitialiser extends ICommentableInitialiser {
	@Override
	public Self instantiate();

}
