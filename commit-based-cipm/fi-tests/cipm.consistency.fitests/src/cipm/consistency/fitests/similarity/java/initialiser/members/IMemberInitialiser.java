package cipm.consistency.fitests.similarity.java.initialiser.members;

import org.emftext.language.java.members.Member;

import cipm.consistency.fitests.similarity.java.initialiser.commons.INamedElementInitialiser;

public interface IMemberInitialiser extends INamedElementInitialiser {
    @Override
    public Member instantiate();
}
