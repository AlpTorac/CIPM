package cipm.consistency.initialisers.eobject.java.types;

import org.emftext.language.java.types.Type;

import cipm.consistency.initialisers.eobject.java.commons.ICommentableInitialiser;

public interface ITypeInitialiser extends ICommentableInitialiser {
	@Override
	public Type instantiate();
}
