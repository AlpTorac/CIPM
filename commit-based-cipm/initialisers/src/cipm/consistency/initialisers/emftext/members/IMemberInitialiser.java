package cipm.consistency.initialisers.emftext.members;

import org.emftext.language.java.members.Member;

import cipm.consistency.initialisers.emftext.commons.INamedElementInitialiser;

public interface IMemberInitialiser extends INamedElementInitialiser {
	@Override
	public Member instantiate();
}
