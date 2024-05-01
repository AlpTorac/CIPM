package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.members.EmptyMember;
import org.emftext.language.java.members.MembersFactory;

import cipm.consistency.fitests.similarity.java.initialiser.IEmptyMemberInitialiser;

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