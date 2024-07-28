package cipm.consistency.fitests.similarity.java.initialiser.members;

import org.emftext.language.java.members.Member;
import org.emftext.language.java.members.MemberContainer;

import cipm.consistency.fitests.similarity.java.initialiser.commons.ICommentableInitialiser;

public interface IMemberContainerInitialiser extends ICommentableInitialiser {
    @Override
    public MemberContainer instantiate();
	public default boolean addMember(MemberContainer mc, Member mbr) {
		if (mbr != null) {
			mc.getMembers().add(mbr);
			return mc.getMembers().contains(mbr);
		}
		return true;
	}
	
	public default boolean addMembers(MemberContainer mc, Member[] mbrs) {
		return this.doMultipleModifications(mc, mbrs, this::addMember);
	}
	public default boolean addDefaultMember(MemberContainer mc, Member mbr) {
		if (mbr != null) {
			mc.getDefaultMembers().add(mbr);
			return mc.getDefaultMembers().contains(mbr);
		}
		return true;
	}
	
	public default boolean addDefaultMembers(MemberContainer mc, Member[] mbrs) {
		return this.doMultipleModifications(mc, mbrs, this::addDefaultMember);
	}
}
