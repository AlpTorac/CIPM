package cipm.consistency.fitests.similarity.eobject.initialiser.java.modifiers;

import org.emftext.language.java.modifiers.Open;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.commons.ICommentableInitialiser;

public interface IOpenInitialiser extends ICommentableInitialiser {
	@Override
	public Open instantiate();

}
