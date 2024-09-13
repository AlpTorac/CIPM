package cipm.consistency.fitests.similarity.java.eobject.initialiser.members;

import org.emftext.language.java.members.Member;

import cipm.consistency.fitests.similarity.java.eobject.initialiser.commons.INamedElementInitialiser;

public interface IMemberInitialiser extends INamedElementInitialiser {
	@Override
	public Member instantiate();
}
