package cipm.consistency.fitests.similarity.java.initialiser.variables;

import org.emftext.language.java.variables.Resource;

import cipm.consistency.fitests.similarity.java.initialiser.commons.ICommentableInitialiser;

public interface IResourceInitialiser extends ICommentableInitialiser {
    @Override
    public Resource instantiate();

}
