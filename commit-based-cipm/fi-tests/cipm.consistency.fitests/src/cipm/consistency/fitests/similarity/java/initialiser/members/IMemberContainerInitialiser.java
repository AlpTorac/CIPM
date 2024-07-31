package cipm.consistency.fitests.similarity.java.initialiser.members;

import org.emftext.language.java.members.Member;
import org.emftext.language.java.members.MemberContainer;

import cipm.consistency.fitests.similarity.java.initialiser.commons.ICommentableInitialiser;

public interface IMemberContainerInitialiser extends ICommentableInitialiser {
	@Override
	public MemberContainer instantiate();

	public default boolean addMember(MemberContainer mc, Member mem) {
		if (mem != null) {
			mc.getMembers().add(mem);
			return mc.getMembers().contains(mem);
		}
		return true;
	}

	public default boolean addMembers(MemberContainer mc, Member[] mems) {
		return this.doMultipleModifications(mc, mems, this::addMember);
	}

	public default boolean addDefaultMember(MemberContainer mc, Member defMem) {
		if (defMem != null) {
			mc.getDefaultMembers().add(defMem);
			return mc.getDefaultMembers().contains(defMem);
		}
		return true;
	}

	public default boolean addDefaultMembers(MemberContainer mc, Member[] defMems) {
		return this.doMultipleModifications(mc, defMems, this::addDefaultMember);
	}
}
