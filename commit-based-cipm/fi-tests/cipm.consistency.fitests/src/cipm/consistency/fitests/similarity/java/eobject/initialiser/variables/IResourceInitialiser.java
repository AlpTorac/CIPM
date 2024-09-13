package cipm.consistency.fitests.similarity.java.eobject.initialiser.variables;

import org.emftext.language.java.variables.Resource;

import cipm.consistency.fitests.similarity.java.eobject.initialiser.commons.ICommentableInitialiser;

public interface IResourceInitialiser extends ICommentableInitialiser {
	@Override
	public Resource instantiate();

}
