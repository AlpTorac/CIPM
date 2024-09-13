package cipm.consistency.fitests.similarity.eobject.initialiser.java.members;

import org.emftext.language.java.members.Member;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.commons.INamedElementInitialiser;

public interface IMemberInitialiser extends INamedElementInitialiser {
	@Override
	public Member instantiate();
}
