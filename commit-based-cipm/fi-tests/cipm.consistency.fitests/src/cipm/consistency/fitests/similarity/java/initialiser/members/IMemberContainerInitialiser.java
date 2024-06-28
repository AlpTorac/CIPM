package cipm.consistency.fitests.similarity.java.initialiser.members;

import org.emftext.language.java.members.Member;
import org.emftext.language.java.members.MemberContainer;

import cipm.consistency.fitests.similarity.java.initialiser.commons.ICommentableInitialiser;

public interface IMemberContainerInitialiser extends ICommentableInitialiser {
	public default void addMember(MemberContainer mc, Member mbr) {
		if (mbr != null) {
			mc.getMembers().add(mbr);
			assert mc.getMembers().contains(mbr);
		}
	}
	
	public default void addMembers(MemberContainer mc, Member[] mbrs) {
		this.addXs(mc, mbrs, this::addMember);
	}
	
	public default void addDefaultMember(MemberContainer mc, Member mbr) {
		if (mbr != null) {
			mc.getDefaultMembers().add(mbr);
			assert mc.getDefaultMembers().contains(mbr);
		}
	}
	
	public default void addDefaultMembers(MemberContainer mc, Member[] mbrs) {
		this.addXs(mc, mbrs, this::addDefaultMember);
	}
}
