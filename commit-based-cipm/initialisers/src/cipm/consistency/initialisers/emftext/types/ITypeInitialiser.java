package cipm.consistency.initialisers.emftext.types;

import org.emftext.language.java.types.Type;

import cipm.consistency.initialisers.emftext.commons.ICommentableInitialiser;

public interface ITypeInitialiser extends ICommentableInitialiser {
	@Override
	public Type instantiate();
}
