package cipm.consistency.fitests.similarity.java.initialiser.types;

import org.emftext.language.java.types.Type;

import cipm.consistency.fitests.similarity.java.initialiser.commons.ICommentableInitialiser;

import org.emftext.language.java.types.Type;

public interface ITypeInitialiser extends ICommentableInitialiser {
    @Override
    public Type instantiate();
}
