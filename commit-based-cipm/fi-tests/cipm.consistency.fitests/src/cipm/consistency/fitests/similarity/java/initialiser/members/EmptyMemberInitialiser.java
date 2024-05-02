package cipm.consistency.fitests.similarity.java.initialiser.members;

import org.emftext.language.java.members.EmptyMember;
import org.emftext.language.java.members.MembersFactory;

public class EmptyMemberInitialiser implements IEmptyMemberInitialiser {
	@Override
	public IEmptyMemberInitialiser newInitialiser() {
		return new EmptyMemberInitialiser();
	}

	@Override
	public EmptyMember instantiate() {
		return MembersFactory.eINSTANCE.createEmptyMember();
	}
}