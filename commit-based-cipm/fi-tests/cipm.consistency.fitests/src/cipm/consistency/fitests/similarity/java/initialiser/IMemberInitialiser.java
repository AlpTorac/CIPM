package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.members.Member;

public interface IMemberInitialiser extends INamedElementInitialiser {
	@Override
	public Member instantiate();
	
	@Override
	public default Member minimalInstantiation() {
		return (Member) INamedElementInitialiser.super.minimalInstantiation();
	}
}
