package cipm.consistency.fitests.similarity.java.initialiser.modifiers;

import org.emftext.language.java.modifiers.Open;

import cipm.consistency.fitests.similarity.java.initialiser.commons.ICommentableInitialiser;

public interface IOpenInitialiser extends ICommentableInitialiser {
    @Override
    public Open instantiate();

}
