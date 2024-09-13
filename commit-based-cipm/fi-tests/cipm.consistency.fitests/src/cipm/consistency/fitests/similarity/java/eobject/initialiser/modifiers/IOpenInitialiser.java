package cipm.consistency.fitests.similarity.java.eobject.initialiser.modifiers;

import org.emftext.language.java.modifiers.Open;

import cipm.consistency.fitests.similarity.java.eobject.initialiser.commons.ICommentableInitialiser;

public interface IOpenInitialiser extends ICommentableInitialiser {
	@Override
	public Open instantiate();

}
