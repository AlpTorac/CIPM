package cipm.consistency.fitests.similarity.java.initialiser.members;

import org.emftext.language.java.members.EmptyMember;
import org.emftext.language.java.members.MembersFactory;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class EmptyMemberInitialiser extends AbstractInitialiserBase implements IEmptyMemberInitialiser {
	@Override
	public IEmptyMemberInitialiser newInitialiser() {
		return new EmptyMemberInitialiser();
	}

	@Override
	public EmptyMember instantiate() {
		return MembersFactory.eINSTANCE.createEmptyMember();
	}
}