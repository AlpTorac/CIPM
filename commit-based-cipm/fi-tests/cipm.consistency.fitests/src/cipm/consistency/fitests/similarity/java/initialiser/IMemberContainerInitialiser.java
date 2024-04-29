package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.Member;
import org.emftext.language.java.members.MemberContainer;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.variables.LocalVariable;

public interface IMemberContainerInitialiser extends ICommentableInitialiser {
	public default void addMember(MemberContainer mc, Member mbr) {
		if (mbr != null) {
			mc.getMembers().add(mbr);
			assert mc.getMembersByName(mbr.getName()).contains(mbr);
		}
	}
	
	public default void addMethod(MemberContainer mc, Method method) {
		if (method != null) {
			mc.getMethods().add(method);
			assert mc.getMethods().contains(method);
			assert mc.getContainedMethod(method.getName()).equals(method);
		}
	}
	
	public default void addField(MemberContainer mc, Field field) {
		if (field != null) {
			mc.getFields().add(field);
			assert mc.getFields().contains(field);
			assert mc.getContainedField(field.getName()).equals(field);
		}
	}
	
	public default void addDefaultMember(MemberContainer mc, Member mbr) {
		if (mbr != null) {
			mc.getDefaultMembers().add(mbr);
			assert mc.getDefaultMembers().contains(mbr);
		}
	}
}
