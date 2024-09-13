package cipm.consistency.fitests.similarity.java.eobject.initialiser.literals;

import org.emftext.language.java.literals.Self;

import cipm.consistency.fitests.similarity.java.eobject.initialiser.commons.ICommentableInitialiser;

public interface ISelfInitialiser extends ICommentableInitialiser {
	@Override
	public Self instantiate();

}
