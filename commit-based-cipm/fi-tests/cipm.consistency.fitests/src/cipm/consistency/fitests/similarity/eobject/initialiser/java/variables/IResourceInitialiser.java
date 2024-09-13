package cipm.consistency.fitests.similarity.eobject.initialiser.java.variables;

import org.emftext.language.java.variables.Resource;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.commons.ICommentableInitialiser;

public interface IResourceInitialiser extends ICommentableInitialiser {
	@Override
	public Resource instantiate();

}
