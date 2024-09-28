package cipm.consistency.initialisers.emftext.members;

import org.emftext.language.java.members.EmptyMember;

public interface IEmptyMemberInitialiser extends IMemberInitialiser {
	@Override
	public EmptyMember instantiate();

}
