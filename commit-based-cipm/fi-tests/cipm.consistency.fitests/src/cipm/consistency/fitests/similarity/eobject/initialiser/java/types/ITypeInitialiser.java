package cipm.consistency.fitests.similarity.eobject.initialiser.java.types;

import org.emftext.language.java.types.Type;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.commons.ICommentableInitialiser;

public interface ITypeInitialiser extends ICommentableInitialiser {
	@Override
	public Type instantiate();
}
