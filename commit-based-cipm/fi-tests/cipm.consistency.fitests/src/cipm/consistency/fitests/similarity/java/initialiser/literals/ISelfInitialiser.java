package cipm.consistency.fitests.similarity.java.initialiser.literals;

import org.emftext.language.java.literals.Self;

import cipm.consistency.fitests.similarity.java.initialiser.commons.ICommentableInitialiser;

public interface ISelfInitialiser extends ICommentableInitialiser {
    @Override
    public Self instantiate();

}
