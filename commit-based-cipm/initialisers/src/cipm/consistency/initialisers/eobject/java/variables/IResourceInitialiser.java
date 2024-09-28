package cipm.consistency.initialisers.eobject.java.variables;

import org.emftext.language.java.variables.Resource;

import cipm.consistency.initialisers.eobject.java.commons.ICommentableInitialiser;

public interface IResourceInitialiser extends ICommentableInitialiser {
	@Override
	public Resource instantiate();

}
