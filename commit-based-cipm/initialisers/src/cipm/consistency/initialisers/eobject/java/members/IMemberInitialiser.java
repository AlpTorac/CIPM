package cipm.consistency.initialisers.eobject.java.members;

import org.emftext.language.java.members.Member;

import cipm.consistency.initialisers.eobject.java.commons.INamedElementInitialiser;

public interface IMemberInitialiser extends INamedElementInitialiser {
	@Override
	public Member instantiate();
}
