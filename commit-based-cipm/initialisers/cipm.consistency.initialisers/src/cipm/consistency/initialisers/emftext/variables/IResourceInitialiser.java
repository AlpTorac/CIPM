package cipm.consistency.initialisers.emftext.variables;

import org.emftext.language.java.variables.Resource;

import cipm.consistency.initialisers.emftext.commons.ICommentableInitialiser;

public interface IResourceInitialiser extends ICommentableInitialiser {
	@Override
	public Resource instantiate();

}
